package com.urms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.urms.entity.Menu;
import com.urms.entity.Role;
import com.urms.entity.RoleMenu;
import com.urms.mapper.MenuMapper;
import com.urms.mapper.RoleMenuMapper;
import com.urms.service.RoleMenuService;

import tk.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public RoleMenu selectByRoleMenuId(Integer roleMenuId) {
        return roleMenuMapper.selectByRoleMenuId(roleMenuId);
    }

    /**
     * @param roleId
     * @param menuIds
     * @return
     */
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
        return menusAll;
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
        //筛选第二级
        for (Menu firstMenu:menuList){
            List<Menu> secMenuList=new ArrayList<>();
            for (Menu menu:menuAll ){
                if (menu.getMenuParentId()==firstMenu.getMenuId()){
                    secMenuList.add(menu);
                }
            }
            firstMenu.setChildren(secMenuList);
        }
        return menuList;
    }

    @Override
    public List<Menu> findMenuByRoles(List<Role> roles) {
        List<Menu> menus=new ArrayList<>();
        if(!CollectionUtils.isEmpty(roles)){
            Set<Integer> menuIds=new HashSet<>();//存放用户的菜单id
            List<RoleMenu> roleMenus;
            for (Role role : roles) {
                //根据角色ID查询权限ID
                QueryWrapper<RoleMenu> roleMenuQueryWrapper=new QueryWrapper<>();
                roleMenuQueryWrapper.eq("ROLE_ID",role.getRoleId());
                roleMenus=roleMenuMapper.selectList(roleMenuQueryWrapper);

                if(!CollectionUtils.isEmpty(roleMenus)){
                    for (RoleMenu roleMenu : roleMenus) {
                        menuIds.add(roleMenu.getMenuId());
                    }
                }
            }
            if(!CollectionUtils.isEmpty(menuIds)){
                for (Integer menuId : menuIds) {
                    //该用户所有的菜单
                    Menu menu = menuMapper.selectByMenuId(menuId);
                    if(menu!=null){
                        menus.add(menu);
                    }
                }
            }
        }
        return menus;
    }
}
