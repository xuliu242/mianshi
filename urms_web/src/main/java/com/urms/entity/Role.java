package com.urms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@TableName("TB_ROLE")
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ROLE_ID")
    private Integer roleId;

    @TableField("ROLE_NAME")
    private String roleName;

    @TableField("ROLE_REMARKS")
    private String roleRemarks;

    @TableField("ROLE_CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date roleCreateTime;

    @TableField("ROLE_HIDDEN")
    private Integer roleHidden;


}
