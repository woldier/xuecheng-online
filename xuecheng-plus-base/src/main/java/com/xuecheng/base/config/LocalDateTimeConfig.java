package com.xuecheng.base.config;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * @author woldier
 * @version 1.0
 * @description
 *  时间的序列化与反序列化
 *  base模块怕配置LocalDateTime序列化与反序列化规范成yyyy-MM-dd HH-mm-ss
 *  并且将其注入到sping容器中，以便其他工程使用（需要注意的是，其他工程的app必须是在com.xuecheng下，否侧无法扫描到）
 * @date 2023/2/15 10:34
 **/
@Configuration
public class LocalDateTimeConfig {
    /**
    * @ 序列化内容
    *  string -> localDateTIme
    * @author  woldier
    *
    */
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer(){
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer(){
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
        };
    }
}
