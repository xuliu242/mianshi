package com.urms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.urms.entity.QueryRoleCondition;
import com.urms.entity.Role;
import com.urms.mapper.RoleMapper;
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
        return roleMapper.insertRole(role);
    }

    @Override
    public int deleteRoleById(Integer roleId) {
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
