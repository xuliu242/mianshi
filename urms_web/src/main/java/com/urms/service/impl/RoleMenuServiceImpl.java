package com.urms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.urms.entity.Menu;
import com.urms.entity.RoleMenu;
import com.urms.mapper.RoleMenuMapper;
import com.urms.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public RoleMenu selectByRoleMenuId(Integer roleMenuId) {
        return roleMenuMapper.selectByRoleMenuId(roleMenuId);
    }

    @Override
    public Boolean insertRoleMenu(Integer roleId, List<Integer> menuIds) {
        //删除已分配菜单权限
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
//        添加新分配的菜单权限
        for (int i = 0; i < menuIds.size(); i++) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuIds.get(i));
            int insertRoleMenu = roleMenuMapper.insertRoleMenu(roleMenu);
            if (insertRoleMenu <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int deleteRoleMenuById(Integer roleMenuId) {
        return roleMenuMapper.deleteRoleMenuById(roleMenuId);
    }

    /**
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> selectByRoleId(Integer roleId) {
        List<Menu> menusAll = roleMenuMapper.selectByRoleId(roleId);
        List<Menu> menuFather = new ArrayList<>();
        List<Menu> menuChildren = new ArrayList<>();
        for (int i = 0; i < menusAll.size(); i++) {
            if (menusAll.get(i).getMenuParentId() == null) {
                menuFather.add(menusAll.get(i));
            } else
                menuChildren.add(menusAll.get(i));

        }
        for (Menu menu : menuFather) {
            List<Menu> menuList = new ArrayList<>();
            for (Menu menuChild : menuChildren) {
                if (menu.getMenuId().equals(menuChild.getMenuParentId())) {
                    menuList.add(menuChild);
                }
            }
            menu.setChildren(menuList);

        }
        return menuFather;
    }

    @Override
    public List<Menu> selectMenuByUserId(Integer userId) {
        List<Menu> menuAll = roleMenuMapper.selectMenuByUserId(userId);
        List<Menu> menuList = new ArrayList<>();
        for (Menu menu : menuAll) {
            if (menu.getMenuParentId() == null) {
                menuList.add(menu);
            }
        }
        return menuList;
    }
}
