package com.xuecheng.content.service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author woldier
 * @version 1.0
 * @description mybatis plus 配置类 当前配置了分页插件
 * @date 2023/2/15 12:13
 **/
@Configuration
@MapperScan("com.xuecheng.content.service.mapper")
public class MybatisPlusConfig {

}
