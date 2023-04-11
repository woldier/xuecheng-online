package com.xuecheng.learning.feignclient;

import com.xuecheng.content.model.po.CoursePublish;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author woldier
 * @version 1.0
 * @description CoursePublishClientFactory 回调工厂
 * @date 2023/4/11 20:50
 **/
@Component
public class CoursePublishClientFactory implements FallbackFactory<CoursePublishClient> {
    @Override
    public CoursePublishClient create(Throwable cause) {
        return new CoursePublishClient() {
            @Override
            public CoursePublish getCoursepublish(Long courseId) {
                return null;
            }
        };
    }
}
