package com.urms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.urms.entity.QueryUserRoleCondition;
import com.urms.entity.Role;
import com.urms.entity.User;
import com.urms.entity.UserRole;
import com.urms.mapper.RoleMapper;
import com.urms.mapper.UserMapper;
import com.urms.mapper.UserRoleMapper;
import com.urms.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<UserRole> selectAll() {
        List<UserRole> userRoleList = userRoleMapper.selectUserRoleByCondition(null);
        for (int i = 0; i < userRoleList.size(); i++) {
            UserRole userRole = userRoleList.get(i);
            userRole.setUserName(userMapper.selectByUserId(userRole.getUserId()).getUserName());
            userRole.setRoleName(roleMapper.selectByRoleId(userRole.getRoleId()).getRoleName());
            userRoleList.set(i,userRole);
        }
        return userRoleList;
    }

    @Override
    public UserRole selectByuserRoleId() {
        return null;
    }

    @Override
    public UserRole selectuserRoleByuserRoleName() {
        return null;
    }

    @Override
    public int insertuserRole(UserRole userRole) {
        return 0;
    }

    @Override
    public int updateuserRoleById(UserRole userRole) {
        return 0;
    }

    @Override
    public int deleteuserRoleById(Integer userRoleId) {
        return 0;
    }

    @Override
    public List<UserRole> selectByQueryUserRoleCondition(QueryUserRoleCondition qurc) {
        return userRoleMapper.selectUserRoleByCondition(qurc);
    }

    @Override
    public List<Integer> selectRoleIds(Integer userId) {
        return userRoleMapper.selectRoleIds(userId);
    }

    @Override
    public Boolean doAssignRoles(Integer userId, Integer[] roleIds) {
        //将已分配的角色信息删除
        int i = userRoleMapper.deleteByUserId(userId);
//        if (i>0){
//            return false;
//        }
        //添加新分配的角色
        User user = userMapper.selectByUserId(userId);
        for (int j = 0; j < roleIds.length; j++) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setUserLoginName(user.getUserLoginName());
            userRole.setRoleId(roleIds[j]);
            int insertUserRole = userRoleMapper.insertUserRole(userRole);
            if (insertUserRole<=0){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Role> selectRolesByUserId(Integer userId) {
        List<Integer> roleIds = userRoleMapper.selectRoleIds(userId);
        List<Role> roleList=new ArrayList<>();
        for (int i = 0; i < roleIds.size(); i++) {
            Role role = roleMapper.selectByRoleId(roleIds.get(i));
            roleList.add(role);
        }
        return roleList;
    }
}
