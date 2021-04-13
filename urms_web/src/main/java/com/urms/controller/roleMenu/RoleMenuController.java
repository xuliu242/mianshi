package com.urms.controller.roleMenu;

import com.urms.entity.Menu;
import com.urms.entity.RoleMenu;
import com.urms.response.Result;
import com.urms.service.MenuService;
import com.urms.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;
    // 根据登录账号id查询菜单
    @RequestMapping("/selectMenuByUserId")
    private Result selectMenuByUserId(Integer userId) {
        List<Menu> menuList = roleMenuService.selectMenuByUserId(userId);
        return Result.ok().data("result",menuList);
    }

    //根据角色权限id查找角色权限信息
    @RequestMapping("/selectByRoleMenuId")
    private Result selectByRoleMenuId(Integer roleMenuId) {
        return Result.error();
    }

    /**
     * 添加角色权限数据
     * @param map
     * @return
     */
    //添加角色权限数据
    @RequestMapping("/insertRoleMenu")
    private Result insertRoleMenu(@RequestBody Map<String,Object> map) {
        Integer roleId = (Integer) map.get("roleId");
        List<Integer> menuIds = (List<Integer>) map.get("menuIds");
        Boolean flag = roleMenuService.insertRoleMenu(roleId, menuIds);
        if (flag){
            return Result.ok();
        }
        return Result.error();
    }

    //根据角色权限ID删除角色权限信息
    @RequestMapping("/deleteRoleMenuById")
    private Result deleteRoleMenuById(Integer roleMenuId) {
        int i = roleMenuService.deleteRoleMenuById(roleMenuId);
        return Result.error();
    }

    //根据角色id查找角色权限信息
    @RequestMapping("/selectByRoleId")
    private Result selectByRoleId(@RequestBody Integer roleId) {
        List<Menu> menuByRoleIdList = roleMenuService.selectByRoleId(roleId);
        List<Integer> menuIds=new ArrayList<>();
        for (Menu menu:menuByRoleIdList){
            menuIds.add(menu.getMenuId());
        }
        List<Menu> menuList = menuService.selectMenuByCondition(null);
        return Result.ok().data("menuList", menuList).data("menuIds",menuIds);
    }

}
