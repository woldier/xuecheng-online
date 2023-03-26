package com.xuecheng;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author woldier
 * @version 1.0
 * @description 启动类
 * @date 2023/2/15 9:34
 **/
@SpringBootApplication
@EnableSwagger2Doc //开启swagger
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
