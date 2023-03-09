package com.xuecheng.media;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/3/9 21:45
 **/
@SpringBootTest
public class SpringMinIOTest {
    @Autowired
    MinioClient minioClient;
    @Test
    public void test(){
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
}
