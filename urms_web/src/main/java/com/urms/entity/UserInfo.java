package com.urms.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserInfo {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 登录名
     */
    private String userLoginName;
    /**
     * 菜单路径
     */
    private Set<String> url;
    /**
     * 权限
     */
    private Set<String> perms;
    /**
     * 角色
     */
    private List<String> roles;



}
