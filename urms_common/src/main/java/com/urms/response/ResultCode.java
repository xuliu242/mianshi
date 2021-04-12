package com.urms.response;

public enum  ResultCode implements CustomResultCode{
    /*
    * 200: "成功"
    *
    * */
    SUCCESS(200,"操作成功"),
    /*
     * 201:"失败"
     * */
    ERROR(201,"操作失败"),
    /*
     * 305:"算术异常"
     * */
    ARITHMETICEXCEPTION(306,"算术异常"),
    /*
     * 5005:"用户不存在"
     * */
    USER_NOT_FOUND_EXCEPTION(5005,"用户不存在"),
    /*
     * 5006:"用户已存在"
     * */
    USER_AlREADY_EXISTS_EXCEPTION(5006,"用户已存在"),
    /*
     * 5007:"角色已存在"
     * */
    ROLE_AlREADY_EXISTS_EXCEPTION(5007,"角色已存在"),
    /*
     * 5008:"角色已分配给用户"
     * */
    ROLE_ASSIGNED_USER_EXCEPTION(5008,"角色已分配给用户"),
    /*
     * 504:"非法访问"
     * */
    ACCESS_EXCEPTION(504,"非法访问"),
    /*
     * 504:"非法访问"
     * */
    TOKEN_ERROR_EXCEPTION(501,"Token过期，请重新登录"),
    ;

    private Integer code;
    private String message;

    ResultCode(Integer code,String message){
        this.code=code;
        this.message=message;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
