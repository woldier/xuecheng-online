package com.xuecheng.ucenter.service;

import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;

/**
 * @author woldier
 * @version 1.0
 * @description 自定义鉴权逻辑
 * @date 2023/4/1 17:20
 **/
public interface AuthService {
    /**
    *
    * description 查询鉴权
    *
    * @param authParamsDto  参数
    * @return com.xuecheng.ucenter.model.dto.XcUserExt
    * @author: woldier
    * @date: 2023/4/1 17:22
    */
    XcUserExt authentic(AuthParamsDto authParamsDto);
}
