package com.urms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class QueryUserRoleCondition {
    private Integer userRoleId;     //用户姓名
    private String userLoginName;   //用户登录名

    private Integer pageSize;       //当前页数据最大条数
    private Integer pageNum;        //当前页码

}
