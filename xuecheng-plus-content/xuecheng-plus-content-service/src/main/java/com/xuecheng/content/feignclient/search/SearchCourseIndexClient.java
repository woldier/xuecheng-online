package com.xuecheng.content.feignclient.search;

import com.xuecheng.base.exception.XueChengPlusException;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author woldier
 * @version 1.0
 * @description 课程信息索引
 * @date 2023/3/28 15:09
 **/
@FeignClient(value = "search" ,fallbackFactory = SearchCourseIndexClientFallbackFactory.class)
@RequestMapping("/search")
public interface SearchCourseIndexClient {
    @PostMapping("/index/course")
    public Boolean add(@RequestBody CourseIndex courseIndex) ;
}
