package com.xuecheng.media;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.*;

/**
 * @author woldier
 * @version 1.0
 * @description MinIO上传测试
 * @date 2023/3/9 18:51
 **/
@SpringBootTest()
public class MinioTest {
    static MinioClient minioClient;

    /**
     * @return void
     * @description 单元测试前通用操作
     * @author: woldier
     * @date: 2023/3/9 19:23
     */
    @BeforeAll
    public static void before() {
        /*
         * 创建MinIO客户端
         * */
        minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
        boolean bucketExists = false;
        /*查看桶是否存在,不存在则创建*/
        try {
            bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("testbucket").build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!bucketExists) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("testbucket").build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @return void
     * @description 上传
     * @author: woldier
     * @date: 2023/3/9 19:22
     */
    @Test
    public void upload() {
        /*通过扩展名获取媒体资源类型*/
        ContentInfo contentInfo = ContentInfoUtil.findExtensionMatch("123.mp4");
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if(contentInfo!=null) mimeType = contentInfo.getMimeType();

        /*上传*/
        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("testbucket")  //桶
                            .object("/test/hello2.zip") // 对象名,在桶下存储的文件
                            .filename("D:\\BaiduNetdiskDownload\\stc-isp.zip")  //指定本地文件路径
                            .contentType(mimeType) //设置媒体文件类型
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
    * @description 删除文件
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/9 19:23
    */
    @Test
    public void delete(){
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket("testbucket")
                            .object("test/hello.zip")
                            .build()
            );

        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
    * @description 查询/下载文件
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/9 20:05
    */
    @Test
    public void download(){
        try {
            /*获取MinIO传给我们的输入流*/
            InputStream object = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket("testbucket")
                            .object("test/hello2.zip")
                            .build());
            /*输出到本地文件系统*/
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\hello_download.zip"));
            IOUtils.copy(object,fileOutputStream);

            /*校验文件完整性
            * 对文件内容进行md5如果inputStream与outputStream一致
            * 下载文件完整
            * 此处用到apache的一个工具类
            * */
            String inputMd5 = DigestUtils.md5Hex(object);
            String downLoadMd5 = DigestUtils.md5Hex(new FileInputStream(new File("D:\\hello_download.zip")));
            String upLoadMd5 = DigestUtils.md5Hex(new FileInputStream(new File("D:\\BaiduNetdiskDownload\\stc-isp.zip")));
            if(inputMd5.equals(downLoadMd5)){
                System.out.println("下载成功");
            }
            if(downLoadMd5.equals(upLoadMd5)){
                System.out.println("本地上传的原始文件与下载到本地的文件md5一致");
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
