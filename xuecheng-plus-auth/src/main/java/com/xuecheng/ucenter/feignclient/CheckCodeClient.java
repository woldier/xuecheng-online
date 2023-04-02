package com.xuecheng.ucenter.feignclient;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author woldier
 * @version 1.0
 * @description checkcode服务远程调用
 * @date 2023/4/1 18:25
 **/
@FeignClient(value = "checkcode", fallbackFactory = CheckCodeFallbackFactory.class)
@RequestMapping("/checkcode")
public interface CheckCodeClient {
    /**
     * description 验证码校验
     *
     * @param key  key
     * @param code 校验码
     * @return java.lang.Boolean
     * @author: woldier
     * @date: 2023/4/1 18:26
     */
    @PostMapping(value = "/verify")
    Boolean verify(@RequestParam("key") String key, @RequestParam("code") String code);
}
