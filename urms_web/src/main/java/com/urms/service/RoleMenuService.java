package com.urms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.urms.entity.Menu;
import com.urms.entity.RoleMenu;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
public interface RoleMenuService extends IService<RoleMenu> {
    RoleMenu selectByRoleMenuId(Integer roleMenuId);
    Boolean insertRoleMenu(Integer roleId,List<Integer> menuIds);
    int deleteRoleMenuById(Integer roleMenuId);
    List<Menu> selectByRoleId(Integer roleId);
    List<Menu> selectMenuByUserId(Integer userId);

}
