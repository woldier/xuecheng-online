package com.xuecheng.content.feignclient.search;

import com.xuecheng.base.exception.XueChengPlusException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author woldier
 * @version 1.0
 * @description 降级处理工厂
 * @date 2023/3/28 15:13
 **/
@Component
@Slf4j
public class SearchCourseIndexClientFallbackFactory implements FallbackFactory<SearchCourseIndexClient> {
    @Override
    public SearchCourseIndexClient create(Throwable cause) {
        log.error("发送熔断执行本地降级方法,{}",cause.getMessage());
        return new SearchCourseIndexClient() {
            @Override
            public Boolean add(CourseIndex courseIndex)  {
                return Boolean.FALSE;
            }
        };
    }
}
