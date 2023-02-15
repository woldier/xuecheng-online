package com.xuecheng.content.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author woldier
 * @version 1.0
 * @description mybatis plus 配置类 当前配置了分页插件
 * @date 2023/2/15 12:13
 **/
@Configuration
@MapperScan("com.xuecheng.content.mapper")
public class MybatisPlusConfig {

   /**
   * @description TODO 
   *  
   * @return  
   * @author  
   * @date  
   */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /*
        * 如果有多种数据库方言,就不给定方言,让其自动推理
        * */
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
