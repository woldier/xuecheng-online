package com.xuecheng.content.feignclient;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.config.MultipartSupportConfig;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author woldier
 * @version 1.0
 * @description openfeign-媒资服务远程调用
 * @date 2023/3/27 15:55
 **/
// 注解未feign远程调用api,value属性执行调用的服务名,configuration进行额外配置,使得能够支持spring-multipart远程调用
//@FeignClient(value = "media-api", configuration = MultipartSupportConfig.class,fallback = MediaServiceClientFallback.class)  //自定义Fallback类
@FeignClient(value = "media-api", configuration = MultipartSupportConfig.class,fallback = MediaServiceClientFallback.class)  //自定义工厂
//@FeignClient(value = "media-api", configuration = MultipartSupportConfig.class,fallback = FallbackFactory.Default.class)  //使用默认工厂
@RequestMapping("/media")
public interface MediaServiceClient {
    @RequestMapping(value = "/upload/coursehtml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void uploadHtml(@RequestPart("filedata") MultipartFile upload, @RequestParam(value = "objectName", required = false) String objectName) throws XueChengPlusException;




}


