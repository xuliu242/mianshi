package com.urms.handle;


import com.urms.response.Result;
import com.urms.response.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandle {
//全局异常，没有指定异常的类型
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }
//  算术异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.error().code(ResultCode.ARITHMETICEXCEPTION.getCode())
                .message(ResultCode.ARITHMETICEXCEPTION.getMessage());
    }
//    自定义异常
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMessage());
    }
}
