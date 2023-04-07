package com.xuecheng.ucenter.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author woldier
 * @version 1.0
 * @description 用户注册请求参数
 * @date 2023/4/6 19:26
 **/
@Data
public class RegisterDto {
    /**
     * 电话
     */
    @NotEmpty(message = "电话号码不能为空")
    private String cellphone;
    /**
     * 校验码
     */
    @NotEmpty(message = "电校验码不能为空")

    private String checkcode;
    /**
     * 校验码key
     */
    @NotEmpty(message = "校验码key不能为空")

    private String checkcodekey;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空")

    private String email;
    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")

    private String nickname;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")

    private String password;
    /**
     * 确认密码
     */
    @NotEmpty(message = "确认密码不能为空")

    private String confirmpwd;
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")

    private String username;

}
