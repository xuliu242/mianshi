package com.urms.handle;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.urms.response.Result;
import com.urms.response.ResultCode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

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
    //    自定义异常
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Result error(UnauthorizedException e){
        e.printStackTrace();
        return Result.error().code(2001).message("没有相关权限");
    }
    //    自定义异常
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Result error(AuthenticationException e){
        e.printStackTrace();
        return Result.error().code(2001).message("没有相关权限");
    }
    //    自定义异常
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public Result error(SQLIntegrityConstraintViolationException e){
        e.printStackTrace();
        return Result.error().code(6005).message(e.getMessage());
    }
    //    自定义异常
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public Result error(TokenExpiredException e){
        e.printStackTrace();
        return Result.error().code(401).message("token 验证错误");
    }
}
