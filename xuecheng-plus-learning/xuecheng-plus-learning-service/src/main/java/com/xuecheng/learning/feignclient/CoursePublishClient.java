package com.xuecheng.learning.feignclient;

import com.xuecheng.content.model.po.CoursePublish;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author woldier
 * @version 1.0
 * @description 发布课程查询远程调用客户端
 * @date 2023/4/11 20:45
 **/
@FeignClient(name = "content-api", fallbackFactory = CoursePublishClientFactory.class)
@RequestMapping("/content")
public interface CoursePublishClient {
    @ResponseBody
    @GetMapping("/r/coursepublish/{courseId}")
    CoursePublish getCoursepublish(@PathVariable("courseId") Long courseId) ;
}
