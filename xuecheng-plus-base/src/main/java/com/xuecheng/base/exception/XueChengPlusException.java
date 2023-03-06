package com.xuecheng.base.exception;

/**
 * @author woldier
 * @version 1.0
 * @description 学成项目的自定义业务异常类
 * @date 2023/3/6 15:06
 **/
public class XueChengPlusException extends Exception{
    private static final long serialVersionUID = 5565760508056698922L;

    private String errMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    /**
    * @description 优雅化代码，使得抛异常的代码更加简洁
    * @param commonError  传入通用异常枚举 如 CommonError.UNKOWN_ERROR
    * @return void
    * @author: woldier
    * @date: 2023/3/6 15:08
    */
    public static void cast(CommonError commonError) throws XueChengPlusException {
        throw new XueChengPlusException(commonError.getErrMessage());
    }
    /**
    * @description 优雅化代码，使得抛异常的代码更加简洁
    * @param errMessage  传入自定义的报错信息
    * @return void
    * @author: woldier
    * @date: 2023/3/6 15:09
    */
    public static void cast(String errMessage) throws XueChengPlusException {
        throw new XueChengPlusException(errMessage);
    }
}
