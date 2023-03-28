package com.xuecheng.content.feignclient;

import com.xuecheng.base.exception.XueChengPlusException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author woldier
 * @version 1.0
 * @description MediaServiceClient 熔断回调
 * @date 2023/3/27 16:25
 **/
//@Component
public class MediaServiceClientFallback implements MediaServiceClient{
    @Override
    public void uploadHtml(MultipartFile upload, String objectName) throws XueChengPlusException {
        XueChengPlusException.cast("media-api服务目前不可达");
    }
}
