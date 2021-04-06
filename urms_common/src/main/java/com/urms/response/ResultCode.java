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
     * 505:"用户不存在"
     * */
    USER_NOT_FOUND_EXCEPTION(505,"用户不存在"),
    /*
     * 504:"非法访问"
     * */
    ACCESS_EXCEPTION(504,"非法访问"),
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
