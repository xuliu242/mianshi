package com.urms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("TB_USER_ROLE")
@ApiModel(value="UserRole对象", description="")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "USER_ROLE_ID", type = IdType.AUTO)
    private Integer userRoleId;

    @ApiModelProperty(value = "外键，参考用户表USER_LOGIN_NAME")
    @TableField("USER_LOGIN_NAME")
    private String userLoginName;

    @TableField("USER_ID")
    private Integer userId;

    @ApiModelProperty(value = "外键，参考角色表ROLE_NAME")
    @TableField("ROLE_ID")
    private Integer roleId;
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String roleName;


}
