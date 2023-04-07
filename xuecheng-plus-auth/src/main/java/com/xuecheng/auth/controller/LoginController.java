package com.xuecheng.auth.controller;

import com.xuecheng.auth.exception.ValidationGroups;
import com.xuecheng.auth.exception.XueChengPlusException;
import com.xuecheng.ucenter.feignclient.CheckCodeClient;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.dto.RegisterDto;
import com.xuecheng.ucenter.model.po.XcUser;
import com.xuecheng.ucenter.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.M
 * @version 1.0
 * @description 测试controller
 * @date 2022/9/27 17:25
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    XcUserMapper userMapper;
    @Autowired
    CheckCodeClient checkCodeClient;

    @Autowired
    RegisterService registerService;
    @RequestMapping("/login-success")
    public String loginSuccess() {
        return "登录成功";
    }


    /**
    *
    * description 注册
    *
    *
    * @return java.lang.String
    * @author: woldier
    * @date: 2023/4/6 19:25
    */
    @PostMapping ("/user/doregister")
    public String register(@RequestBody @Validated(value = {ValidationGroups.Inster.class}) RegisterDto registerDto) throws XueChengPlusException {
        Boolean verify = checkCodeClient.verify(registerDto.getCheckcodekey(), registerDto.getCheckcode());
        if(!verify) XueChengPlusException.cast("效验错误");
        boolean b = registerService.registerUser(registerDto);
        return "注册成功";
    }
    @PostMapping("/user/findpassword")
    public void findPassword(@RequestBody @Validated(value = {ValidationGroups.Update.class}) RegisterDto registerDto) throws XueChengPlusException {
        Boolean verify = checkCodeClient.verify(registerDto.getCheckcodekey(), registerDto.getCheckcode());
        if(!verify) XueChengPlusException.cast("效验错误");
        registerService.findPassword(registerDto);
    }

    @RequestMapping("/user/{id}")
    public XcUser getuser(@PathVariable("id") String id) {
        XcUser xcUser = userMapper.selectById(id);
        return xcUser;
    }

    @RequestMapping("/r/r1")
    @PreAuthorize(value = "hasAuthority('p1')")
    public String r1() {
        return "访问r1资源";
    }

    @RequestMapping("/r/r2")
    @PreAuthorize(value = "hasAuthority('p2')")
    public String r2() {
        return "访问r2资源";
    }



}
