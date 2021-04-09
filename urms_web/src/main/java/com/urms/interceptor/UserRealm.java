package com.urms.interceptor;


import com.urms.entity.ActiveUser;
import com.urms.entity.Menu;
import com.urms.entity.Role;
import com.urms.entity.User;
import com.urms.mapper.RoleMapper;
import com.urms.mapper.RoleMenuMapper;
import com.urms.service.RoleMenuService;
import com.urms.service.RoleService;
import com.urms.service.UserRoleService;
import com.urms.service.UserService;
import com.urms.utils.JWTUtils;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuServicen;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        if(activeUser==null){
            authorizationInfo.addStringPermission("*:*");
        }else {
            List<String> permissions = new ArrayList<>(activeUser.getPermissions());
            List<Role> roleList = activeUser.getRoles();
            //授权角色
            if (!CollectionUtils.isEmpty(roleList)) {
                for (Role role : roleList) {
                    authorizationInfo.addRole(role.getRoleName());
                }
            }
            //授权权限
            if (!CollectionUtils.isEmpty(permissions)) {
                for (String  permission : permissions) {
                    if (permission != null && !"".equals(permission)) {
                        authorizationInfo.addStringPermission(permission);
                    }
                }
            }
        }
        System.out.println(authorizationInfo.toString());
        System.out.println(authorizationInfo.getRoles().toString());
        System.out.println(authorizationInfo.getStringPermissions().toString());
        return authorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtils.getUsername(token);

        if (StringUtils.isEmpty(username)) {
            throw new AuthenticationException(" token错误，请重新登入！");
        }

        User userBean = userService.selectUserByLoginName(username);

        if (Objects.isNull(userBean)) {
            throw new AccountException("账号不存在!");
        }
        if(JWTUtils.isExpire(token)){
            throw new AuthenticationException(" token过期，请重新登入！");
        }

        if (! JWTUtils.verify(token, username, userBean.getUserPassword())) {
            throw new CredentialsException("密码错误!");
        }

        if(userBean.getUserStatus().equals(0)){
            throw new LockedAccountException("账号已被锁定!");
        }

        //如果验证通过，获取用户的角色
        List<Role> roles= userRoleService.selectRolesByUserId(userBean.getUserId());
        //查询用户的所有菜单(包括了菜单和按钮)
        List<Menu> menus=roleMenuServicen.findMenuByRoles(roles);

        Set<String> urls=new HashSet<>();
        Set<String> perms=new HashSet<>();
        if(!CollectionUtils.isEmpty(menus)){
            for (Menu menu : menus) {
                String url = menu.getMenuUrl();
                String per = menu.getMenuOpcode();
                if(menu.getMenuType().equals("1")&& !StringUtils.isEmpty(url)){
                    urls.add(menu.getMenuUrl());
                }
                if(menu.getMenuType().equals("2")&&!StringUtils.isEmpty(per)){
                    perms.add(menu.getMenuOpcode());
                }
            }
        }
        //过滤出url,和用户的权限
        ActiveUser activeUser = new ActiveUser();
        activeUser.setRoles(roles);
        activeUser.setUser(userBean);
        activeUser.setMenus(menus);
        activeUser.setUrls(urls);
        activeUser.setPermissions(perms);

        System.out.println(activeUser.toString());
        System.out.println("activeUser.getPermissions()"+activeUser.getPermissions());
        System.out.println("activeUser.getRoles()"+activeUser.getRoles().toString());
        System.out.println("activeUser.getUrls()"+activeUser.getUrls().toString());
        return new SimpleAuthenticationInfo(activeUser, token, getName());
    }
}
