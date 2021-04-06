package com.urms.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TB_USER")
@ApiModel(value="User对象", description="")
@KeySequence(value = "U_SEQ", clazz = Integer.class)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "USER_ID",type = IdType.INPUT)
    private Integer userId;

    @TableField("USER_LOGIN_NAME")
    private String userLoginName;

    @TableField("USER_PASSWORD")
    private String userPassword;

    @TableField("USER_NAME")
    private String userName;

    @TableField("USER_PHONE")
    private Long userPhone;

    @TableField("USER_EMAIL")
    private String userEmail;

    @TableField("USER_SEX")
    private String userSex;

    @ApiModelProperty(value = "可取0或1（0无效，1有效）")
    @TableField("USER_STATUS")
    private String userStatus;

    @TableField("USER_CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userCreateTime;

    @TableField("USER_REMARKS")
    private String userRemarks;

    @ApiModelProperty(value = "可取0或1")
    @TableField("USER_HIDDEN")
    private Integer userHidden;

    @TableField("USER_UPDATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userUpdateTime;


}
