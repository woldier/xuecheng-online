package com.xuecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author woldier
 * @version 1.0
 * @description content 服务 启动类
 * @date 2023/2/15 12:03
 **/
@SpringBootApplication
//@EnableFeignClients(basePackages={"com.xuecheng.content.feignclient"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
