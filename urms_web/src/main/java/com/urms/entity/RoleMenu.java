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
@TableName("TB_ROLE_MENU")
@ApiModel(value="RoleMenu对象", description="")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ROLE_MENU_ID", type = IdType.AUTO)
    private Integer roleMenuId;

    @ApiModelProperty(value = " 外键，参考角色表ROLE_NAME")
    @TableField("ROLE_ID")
    private Integer roleId;

    @ApiModelProperty(value = "外键，参考菜单表MENU_NAME")
    @TableField("MENU_ID")
    private Integer menuId;


}
