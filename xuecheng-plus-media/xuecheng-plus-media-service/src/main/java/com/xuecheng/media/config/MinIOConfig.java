package com.xuecheng.media.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/3/9 21:33
 **/
@Configuration
@EnableConfigurationProperties({MinIOProperties.class})
@RequiredArgsConstructor
public class MinIOConfig {
    private final MinIOProperties minIOProperties;

    /**
    * @description 初始化MinIO客户端,并且交给spring管理
    *
    * @return io.minio.MinioClient
    * @author: woldier
    * @date: 2023/3/9 21:36
    */
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
    }
}
