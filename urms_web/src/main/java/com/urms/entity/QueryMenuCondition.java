package com.urms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class QueryMenuCondition {
    private String menuName;    //菜单名
    private String menuStatus;  //菜单状态
    private Integer pageSize;   //当前页数据最大条数
    private Integer pageNum;     //当前页码
    private String sort;        //排序方式

}
