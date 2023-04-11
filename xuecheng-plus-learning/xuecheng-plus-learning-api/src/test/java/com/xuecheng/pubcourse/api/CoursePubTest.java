package com.xuecheng.pubcourse.api;

import com.xuecheng.learning.feignclient.CoursePublishClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布测试
 * @date 2023/4/11 21:07
 **/

@SpringBootTest
public class CoursePubTest {
    @Autowired
    CoursePublishClient coursePublishClient;

    @Test
    public void test1(){
        System.out.println(coursePublishClient.getCoursepublish(2L));
    }
}
