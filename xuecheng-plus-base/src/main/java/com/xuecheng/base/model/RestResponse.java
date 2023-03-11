package com.xuecheng.base.model;

import lombok.Data;

/**
 * @author woldier
 * @version 1.0
 * @description 通用rest返回结果类
 * @date 2023/3/11 11:46
 **/
@Data
public  class  RestResponse<T> {
    /**
     * 响应编码,0为正常,-1错误
     */
    private int code;

    /**
     * 响应提示信息
     */
    private String msg;

    /**
     * 响应内容
     */
    private T result;


    public RestResponse() {
        this(0, "success");
    }

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 错误信息的封装
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RestResponse<T> validfail(String msg) {
        RestResponse<T> response = new RestResponse<T>();
        response.setCode(-1);
        response.setMsg(msg);
        return response;
    }



    /**
     * 添加正常响应数据（包含响应内容）
     *
     * @return RestResponse Rest服务封装相应数据
     */
    public static <T> RestResponse<T> success(T result) {
        RestResponse<T> response = new RestResponse<T>();
        response.setResult(result);
        return response;
    }

    /**
     * 添加正常响应数据（不包含响应内容）
     *
     * @return RestResponse Rest服务封装相应数据
     */
    public static <T> RestResponse<T> success() {
        return new RestResponse<T>();
    }


    public Boolean isSuccessful() {
        return this.code == 0;
    }


}
