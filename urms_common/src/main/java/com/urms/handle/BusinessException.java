package com.urms.handle;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

//自定义异常类
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "错误信息")
    private String message;
}
