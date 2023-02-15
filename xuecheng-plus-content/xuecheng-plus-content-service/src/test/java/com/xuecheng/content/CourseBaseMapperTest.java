package com.xuecheng.content;

import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.po.CourseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author woldier
 * @version 1.0
 * @description 课程内容测试类
 * @date 2023/2/15 12:53
 **/
@SpringBootTest
public class CourseBaseMapperTest {
    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Test
    void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(74L);
        Assertions.assertNotNull(courseBase);
    }
}
