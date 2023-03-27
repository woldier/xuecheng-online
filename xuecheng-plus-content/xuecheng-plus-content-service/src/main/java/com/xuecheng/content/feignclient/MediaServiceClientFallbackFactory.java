package com.xuecheng.content.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author woldier
 * @version 1.0
 * @description 回调工厂
 * @date 2023/3/27 16:31
 **/
@Slf4j
public class MediaServiceClientFallbackFactory implements FallbackFactory<MediaServiceClient> {
    @Override
    public MediaServiceClient create(Throwable throwable) {
        log.error("网络目前无法访问,err{}",throwable.getMessage());
        return new MediaServiceClientFallback();
    }
}
