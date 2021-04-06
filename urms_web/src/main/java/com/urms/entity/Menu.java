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
import java.math.BigDecimal;
import java.util.List;

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
@TableName("TB_MENU")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Integer menuId;

    @TableField("MENU_TYPE")
    private String menuType;

    @TableField("MENU_NAME")
    private String menuName;

    @TableField("MENU_PARENT_ID")
    private Integer menuParentId;

    @ApiModelProperty(value = "可取0或1（0无效，1有效）")
    @TableField("MENU_STATUS")
    private String menuStatus;

    @TableField("MENU_QUEUE_NUMBER")
    private BigDecimal menuQueueNumber;

    @TableField("MENU_URL")
    private String menuUrl;

    @TableField("MENU_OPCODE")
    private String menuOpcode;

    @TableField("MENU_REMARKS")
    private String menuRemarks;

    @ApiModelProperty(value = "可取0或1")
    @TableField("MENU_HIDDEN")
    private Integer menuHidden;
    @TableField(exist = false)
    private List<Menu> children;


}
