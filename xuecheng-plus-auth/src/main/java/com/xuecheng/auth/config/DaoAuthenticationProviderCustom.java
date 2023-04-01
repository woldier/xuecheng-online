package com.xuecheng.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author woldier
 * @version 1.0
 * @description 重写DaoAuthenticationProvider 中的鉴权方法
 * @date 2023/4/1 16:53
 **/
@Component

@Slf4j
public class DaoAuthenticationProviderCustom extends DaoAuthenticationProvider {
    /**
    *
    * description 设置自定义的UserDetailsServiceImpl到DaoAuthenticationProvider中
    *
    * @param userDetailsService
    * @return void
    * @author: woldier
    * @date: 2023/4/1 16:56
    */
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    /**
    *
    * description 重写密码校验方法 ,不做任何事情
    *
    * @param userDetails
     * @param authentication
    * @return void
    * @author: woldier
    * @date: 2023/4/1 16:56
    */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.debug("执行自定义additionalAuthenticationChecks");
    }
}
