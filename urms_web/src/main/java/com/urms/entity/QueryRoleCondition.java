package com.urms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class QueryRoleCondition {
    private String roleName;    //角色名
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date roleCreateTime;    //角色创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date roleStopTime;    //角色创建时间
    private Integer pageSize;    //当前页数据最大条数
    private Integer pageNum;    //当前页码
}
