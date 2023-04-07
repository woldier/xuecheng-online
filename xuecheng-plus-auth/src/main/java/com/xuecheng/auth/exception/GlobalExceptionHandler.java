package com.xuecheng.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 全局异常处理器
 * @date 2023/3/6 15:18
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
    * @description XueChengPlusException.class 异常处理，提取异常信息并且返回
    * @param e
    * @return com.xuecheng.base.exception.RestErrorResponse
    * @author: woldier
    * @date: 2023/3/6 15:22
    */
    @ExceptionHandler(XueChengPlusException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse doXueChengPlusException(XueChengPlusException e){
        return new RestErrorResponse(e.getErrMessage());
    }

    /**
    * @description Exception 异常处理，此异常为未知异常
    * @param e
    * @return com.xuecheng.base.exception.RestErrorResponse
    * @author: woldier
    * @date: 2023/3/6 15:27
    */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse doException(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        if("不允许访问".equals(e.getMessage()))
            return new RestErrorResponse("没有操作此功能的权限");
        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }

    /**
    * @description Controller JSR303校验异常
    * @param e
    * @return com.xuecheng.base.exception.RestErrorResponse
    * @author: woldier
    * @date: 2023/3/6 16:46
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse doMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        StringBuffer errMsg = new StringBuffer();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(error -> {
            errMsg.append(error.getDefaultMessage()).append(",");
        });
        log.error(errMsg.toString());
        return new RestErrorResponse(errMsg.toString());
    }
}
