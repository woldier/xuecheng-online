package com.xuecheng.content;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.config.MultipartSupportConfig;
import com.xuecheng.content.feignclient.MediaServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@SpringBootTest
public class FeignUploadTest {

    @Autowired
    MediaServiceClient mediaServiceClient;

    //远程调用，上传文件
    @Test
    public void test() throws XueChengPlusException {
    
        MultipartFile multipartFile = MultipartSupportConfig.getMultipartFile(new File("D:\\java_lesson\\11.html"));
        mediaServiceClient.uploadHtml(multipartFile,"12.html");
    }

}
