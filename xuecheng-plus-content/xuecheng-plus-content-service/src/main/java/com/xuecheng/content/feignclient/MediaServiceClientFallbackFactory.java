package com.xuecheng.content.feignclient;

import com.xuecheng.base.exception.XueChengPlusException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author woldier
 * @version 1.0
 * @description 回调工厂
 * @date 2023/3/27 16:31
 **/
@Slf4j
@Component
public class MediaServiceClientFallbackFactory implements FallbackFactory<MediaServiceClient> {
    @Override
    public MediaServiceClient create(Throwable throwable) {
        log.error("网络目前无法访问,err{}",throwable.getMessage());
        return new MediaServiceClient() {
            @Override
            public void uploadHtml(MultipartFile upload, String objectName) throws XueChengPlusException {
                XueChengPlusException.cast("media-api服务目前不可达");
            }
        };
    }
}
