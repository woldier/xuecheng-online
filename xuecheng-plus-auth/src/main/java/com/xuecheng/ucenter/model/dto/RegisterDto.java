package com.xuecheng.ucenter.model.dto;

/**
 * @author woldier
 * @version 1.0
 * @description 用户注册请求参数
 * @date 2023/4/6 19:26
 **/
public class RegisterDto {
    /**
     * 电话
     */
    private String cellphone;
    /**
     * 校验码
     */
    private String checkcode;
    /**
     * 校验码key
     */
    private String checkcodekey;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String confirmpwd;
    /**
     * 用户名
     */
    private String username;

}
