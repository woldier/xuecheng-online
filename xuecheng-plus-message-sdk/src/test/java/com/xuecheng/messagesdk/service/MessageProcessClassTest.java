package com.xuecheng.messagesdk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author woldier
 * @version 1.0
 * @description MessageProcessClass测试类
 * @date 2023/3/27 9:27
 **/
@SpringBootTest
public class MessageProcessClassTest {
    @Autowired
    MessageProcessClass messageProcessClass;

    @Test
    public void testMessageProcessClass(){
        messageProcessClass.process(0,1,"course_publish",2,3600);
    }
}
