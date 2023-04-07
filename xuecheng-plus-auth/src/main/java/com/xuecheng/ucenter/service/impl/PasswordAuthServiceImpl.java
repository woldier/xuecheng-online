package com.xuecheng.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.xuecheng.auth.exception.XueChengPlusException;
import com.xuecheng.ucenter.feignclient.CheckCodeClient;
import com.xuecheng.ucenter.mapper.XcMenuMapper;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.RegisterDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.model.po.XcMenu;
import com.xuecheng.ucenter.model.po.XcUser;
import com.xuecheng.ucenter.service.AuthService;
import com.xuecheng.ucenter.service.RegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author woldier
 * @version 1.0
 * @description 用户名密码鉴权
 * @date 2023/4/1 17:23
 **/
@Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService, RegisterService {
    @Autowired
    XcUserMapper xcUserMapper;

    @Autowired
    XcMenuMapper xcMenuMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CheckCodeClient checkCodeClient;

    @Override
    public XcUserExt authentic(AuthParamsDto authParamsDto) {
        /*校验验证码*/
        if (StringUtils.isEmpty(authParamsDto.getCheckcode()) ||
                StringUtils.isEmpty(authParamsDto.getCheckcodekey()))
            throw new RuntimeException("验证码为空");

        if (!checkCodeClient.verify(authParamsDto.getCheckcodekey(), authParamsDto.getCheckcode())) {
            throw new RuntimeException("验证码校验失败");
        }
        //账号
        String username = authParamsDto.getUsername();
        XcUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));
        if (user == null) {
            //返回空表示用户不存在
            throw new RuntimeException("账号不存在");
        }
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(user, xcUserExt);
        //校验密码
        //取出数据库存储的正确密码
        String passwordDb = user.getPassword();
        String passwordForm = authParamsDto.getPassword();
        boolean matches = passwordEncoder.matches(passwordForm, passwordDb);
        if (!matches) {
            throw new RuntimeException("账号或密码错误");
        }
        //查询权限
        List<String> stringList = xcMenuMapper.selectPermissionByUserId(xcUserExt.getId()).stream().map(XcMenu::getCode).collect(Collectors.toList());
        xcUserExt.setPermissions(stringList);
        return xcUserExt;

    }

    @Override
    @Transactional
    public boolean registerUser(RegisterDto dto) throws XueChengPlusException {
        /**
         * 1.查询用户username是否存在
         * 2.密码转码
         * 存入数据库
         */
        if (!dto.getPassword().equals(dto.getConfirmpwd()))
            XueChengPlusException.cast("输入的密码不匹配");

        LambdaQueryWrapper<XcUser> q = new LambdaQueryWrapper<>();
        q.eq(XcUser::getUsername, dto.getUsername());
        XcUser xcUser = xcUserMapper.selectOne(q);
        if (xcUser != null) XueChengPlusException.cast("当前用户存在,请直接登录");

        String pwd = getPwd(dto);

        XcUser user = new XcUser();
        BeanUtils.copyProperties(dto, user);
        user.setName(user.getNickname());
        user.setPassword(pwd);
        user.setUtype("101001");
        user.setStatus("1");
        user.setCreateTime(LocalDateTime.now());
        xcUserMapper.insert(user);

        return true;
    }

    private static String getPwd(RegisterDto dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = dto.getPassword();
        pwd = encoder.encode(pwd);
        return pwd;
    }

    @Override
    public boolean findPassword(RegisterDto dto) throws XueChengPlusException {
        if (StringUtils.isEmpty(dto.getCellphone()) && StringUtils.isEmpty(dto.getEmail()))
            XueChengPlusException.cast("电话号码和邮箱不能同时为空");
        LambdaQueryWrapper<XcUser> q = new LambdaQueryWrapper<>();
        q.eq(!StringUtils.isEmpty(dto.getCellphone()), XcUser::getCellphone, dto.getCellphone());
        q.eq(!StringUtils.isEmpty(dto.getEmail()), XcUser::getEmail, dto.getEmail());
        XcUser xcUser = xcUserMapper.selectOne(q);
        if (xcUser == null) XueChengPlusException.cast("当前用户不存在");

        String pwd = getPwd(dto);

        xcUser.setPassword(pwd);

        xcUserMapper.updateById(xcUser);

        return false;
    }
}
