package com.urms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.urms.entity.QueryRoleCondition;
import com.urms.entity.Role;
import com.urms.handle.BusinessException;
import com.urms.mapper.RoleMapper;
import com.urms.mapper.UserRoleMapper;
import com.urms.response.ResultCode;
import com.urms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public List<Role> selectAll() {
        List<Role> roles = roleMapper.selectRoleByCondition(null);

        return roles;
    }

    @Override
    public Role selectByRoleId(Integer roleId) {
        return roleMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Role> selectRoleByRoleName(String roleName) {
        return null;
    }

    @Override
    public int insertRole(Role role) {
        Role selectByRoleName = roleMapper.selectByRoleName(role.getRoleName());
        if (selectByRoleName!=null){
            throw new BusinessException(ResultCode.ROLE_AlREADY_EXISTS_EXCEPTION.getCode(), "角色名已存在");
        }
        return roleMapper.insertRole(role);
    }

    @Override
    public int deleteRoleById(Integer roleId) {
        List<Integer> userIds = userRoleMapper.selectUserIds(roleId);
        if (userIds!=null){
            throw new BusinessException(ResultCode.ROLE_ASSIGNED_USER_EXCEPTION.getCode(), "角色已分配给用户");
        }
        return roleMapper.deleteRoleById(roleId);
    }

    @Override
    public int updateRoleById(Role role) {
        return roleMapper.updateRoleById(role);
    }

    @Override
    public List<Role> selectRoleByCondition(QueryRoleCondition condition) {
        return roleMapper.selectRoleByCondition(condition);
    }
}
