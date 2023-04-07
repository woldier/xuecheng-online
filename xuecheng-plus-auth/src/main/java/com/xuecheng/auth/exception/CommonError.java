package com.xuecheng.auth.exception;

/**
 * @author woldier
 * @version 1.0
 * @description 通用的错误信息枚举类
 * @date 2023/3/6 15:01
 **/
public enum CommonError {
    UNKOWN_ERROR("执行过程异常，请重试。"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");

    private String errMessage;

    public String getErrMessage() {
        return errMessage;
    }

    private CommonError( String errMessage) {
        this.errMessage = errMessage;
    }

    /**
    * @description 使用方法案例
    * @param args
    * @return void
    * @author: woldier
    * @date: 2023/3/6 15:04
    */
    public static void main(String[] args) {
        System.out.println(CommonError.PARAMS_ERROR.getErrMessage());
    }
}
