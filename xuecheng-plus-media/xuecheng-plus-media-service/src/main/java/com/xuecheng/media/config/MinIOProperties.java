package com.xuecheng.media.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/3/9 21:31
 **/
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinIOProperties {
}
