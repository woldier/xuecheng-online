package com.xuecheng.ucenter.service;

import com.xuecheng.auth.exception.XueChengPlusException;
import com.xuecheng.ucenter.model.dto.RegisterDto;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/4/6 19:38
 **/
public interface RegisterService {
    /**
    *
    * description 用户注册
    *
    * @param  dto 用户注册信息参数
    * @return boolean
    * @author: woldier
    * @date: 2023/4/6 19:39
    */
    boolean registerUser(RegisterDto dto) throws XueChengPlusException;
}
