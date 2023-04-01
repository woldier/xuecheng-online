package com.xuecheng.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.po.XcUser;
import lombok.RequiredArgsConstructor;
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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        XcUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));
        if (user == null) {
            //返回空表示用户不存在
            return null;
        }
        //取出数据库存储的正确密码
        String password = user.getPassword();
        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
        String[] authorities = {"test"};
        //为了安全在令牌中不放密码
        user.setPassword(null);
        //将user对象转json
        String userString = JSON.toJSONString(user);

        //创建UserDetails对象,权限信息待实现授权功能时再向UserDetail中加入
        return User.withUsername(userString).password(password).authorities(authorities).build();
    }

}
