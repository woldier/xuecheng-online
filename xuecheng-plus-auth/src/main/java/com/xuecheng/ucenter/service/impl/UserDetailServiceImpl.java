package com.xuecheng.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.model.po.XcUser;
import com.xuecheng.ucenter.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author woldier
 * @version 1.0
 * @description UserDetailsService 实现类
 * @date 2023/4/1 12:06
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final XcUserMapper xcUserMapper;

    /**
     * description 从数据库中查询数据封装成UserDetails返回
     *
     * @param username 用户名
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author: woldier
     * @date: 2023/4/1 12:08
     */
    private final ApplicationContext applicationContext;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        AuthParamsDto authParamsDto = null;
        try {
            authParamsDto = JSON.parseObject(username, AuthParamsDto.class);
        } catch (Exception e) {
            log.error("获取认证参数出错");
            throw new RuntimeException("获取认证参数出错");
        }
        String beanName = authParamsDto.getAuthType() + "_authservice";
        AuthService authService = null;
        try {
            authService = applicationContext.getBean(beanName, AuthService.class); //更具类型加载bean
        } catch (BeansException e) {
            throw new RuntimeException("暂时不支持的认证类型");
        }
        XcUserExt authentic = authService.authentic(authParamsDto);//鉴权
        return getUserPrincipal(authentic);


    }

    /**
     * description 根据XcUserExt封装UserDetails 返回
     *
     * @param user
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author: woldier
     * @date: 2023/4/1 17:31
     */
    public UserDetails getUserPrincipal(XcUserExt user) {
        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
        String[] authorities = user.getPermissions().toArray(new String[0]);
        String password = user.getPassword();
        //为了安全在令牌中不放密码
        user.setPassword(null);
        //将user对象转json
        String userString = JSON.toJSONString(user);
        //创建UserDetails对象
        return User.withUsername(userString).password(password).authorities(authorities).build();

    }


}
