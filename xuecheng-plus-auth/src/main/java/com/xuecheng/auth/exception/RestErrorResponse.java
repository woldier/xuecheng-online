package com.xuecheng.auth.exception;

import java.io.Serializable;

/**
 * @author woldier
 * @version 1.0
 * @description 客户端异常返回信息类
 * @date 2023/3/6 15:12
 **/
public class RestErrorResponse implements Serializable {
    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
