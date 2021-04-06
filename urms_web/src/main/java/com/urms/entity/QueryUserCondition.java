package com.urms.entity;

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
public class QueryUserCondition {
    private String loginName;   //用户名
    private String userName;    //姓名
    private String status;      //用户状态
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;     //查询起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopTime;      //查询截止时间
    private Integer pageSize;   //当前页数据最大条数
    private Integer pageNum;    //当前页码

}
