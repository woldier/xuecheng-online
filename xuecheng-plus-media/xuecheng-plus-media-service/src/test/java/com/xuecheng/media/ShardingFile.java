package com.xuecheng.media;

import io.minio.ComposeObjectArgs;
import io.minio.ComposeSource;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author woldier
 * @version 1.0
 * @description 文件分片上传与合并的实现测试
 * @date 2023/3/11 8:48
 **/
@SpringBootTest
public class ShardingFile {

    @Autowired
    MinioClient minioClient;
    /**
     * @return void
     * @description 本地文件分片
     * @author: woldier
     * @date: 2023/3/11 8:50
     */
    @Test
    public void shading() throws IOException {
        /*
         * 1.指定本地文件路径，分片大小，分片后文件存储位置
         * 2.根据文件大小与分片大小得到分片数目，创建对应文件
         * 3.分片写入
         * */
        //本地源文件
        File source = new File("D:\\java_lesson\\sharding\\visualcrustdemo.rar");
        FileInputStream inputStream = new FileInputStream(source);
        //分片保存的文件夹
        File chunkDir = new File("D:\\java_lesson\\sharding\\chunk");
        //分片大小
        Long chunkSize = 1024 * 1024 * 5L;
        //分片数目
        int shardingNum = (int) Math.ceil((source.length() * 1.0 / chunkSize));
        //缓冲区
        byte[] b = new byte[1024];
        for (int i = 0; i < shardingNum; i++) {
            //创建分片文件
            File shadedFile = new File(chunkDir, String.valueOf(i));
            //创建分片文件输出流
            FileOutputStream shaded = new FileOutputStream(shadedFile);
            int len = -1;
            while ((len = inputStream.read(b)) != -1) {
                shaded.write(b, 0, len);
                if (shadedFile.length() >= chunkSize) break;  //当该分片达到分片大小时跳出
            }
            shaded.close();


        }
        inputStream.close();


    }


    /**
    * @description 分片文件聚合
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/11 10:36
    */
    @Test
    public void merging() throws IOException {
        /*
         * 1.从分片文件夹中获取所有文件
         * 2.写入
         * */
        //分片文件目录
        File sourceDir = new File("D:\\java_lesson\\sharding\\chunk");
        //目标文件
        File target = new File("D:\\java_lesson\\sharding\\visualcrustdemo_merge.rar");
        //目标文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        String[] list = sourceDir.list();
        assert list != null;
        /*文件排序*/
        List<String> fileListSorted = Arrays.asList(list).stream().sorted(Comparator.comparing(Integer::valueOf)).collect(Collectors.toList());
        byte[] b = new byte[1024];
        fileListSorted.forEach(
                e -> {
                    try {
                        FileInputStream inputStream = new FileInputStream(new File(sourceDir, e));
                        int len = -1;
                        while((len=inputStream.read(b)) != -1){
                            fileOutputStream.write(b,0,len);
                        }
                        inputStream.close();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        fileOutputStream.close();
    }


    /**
    * @description 本地分片上传到minio并合并
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/11 10:38
    */
    @Test
    public void MinIOMerge() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //分片文件目录
        File sourceDir = new File("D:\\java_lesson\\sharding\\chunk");
        String[] list = sourceDir.list();
        assert list != null;
        /*文件排序*/
        Arrays.stream(list).sorted(Comparator.comparing(Integer::valueOf)).forEach(
                i ->{
                    /*上传*/
                    try {
                        minioClient.uploadObject(
                                UploadObjectArgs.builder()
                                        .bucket("testbucket")  //桶
                                        .object("/test/chunk/"+i) // 对象名,在桶下存储的文件
                                        .filename("D:\\java_lesson\\sharding\\chunk\\"+i)  //指定本地文件路径

                                        .build()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        List<ComposeSource> composeSourceList = Arrays.asList(list).stream().map(e ->

                ComposeSource.builder().bucket("testbucket").object("/test/chunk/" + e).build()
        ).collect(Collectors.toList());
        /*minio文件合并*/
        ComposeObjectArgs composeObjectArgs = ComposeObjectArgs.builder().bucket("testbucket").object("/test/conpose.rar").sources(composeSourceList).build();
        minioClient.composeObject(composeObjectArgs);


    }
}
