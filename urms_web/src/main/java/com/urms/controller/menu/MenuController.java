package com.urms.controller.menu;

import com.urms.entity.Menu;
import com.urms.entity.QueryMenuCondition;
import com.urms.response.Result;
import com.urms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    // 根据菜单id查找菜单信息
    @RequestMapping("/selectByMenuId")
    public Result selectByMenuId(Integer menuId) {
        Menu menu = menuService.selectByMenuId(menuId);
        if (menu!=null){
            return Result.ok().data("result",menu);
        }
        return Result.error();
    }
    // 根据菜单名查找菜单信息
    @RequestMapping("/selectByMenuName")
    public Result selectByMenuName(String menuName) {
        Menu menu = menuService.selectMenuByMenuName(menuName);
        if (menu!=null){
            return Result.ok().data("result",menu);
        }
        return Result.error();
    }
    // 条件查询
    @RequestMapping("/selectMenuByCondition")
    public Result selectMenuByCondition(@RequestBody QueryMenuCondition qmc) {
        qmc.setPageNum(null);
        qmc.setPageSize(null);
        List<Menu> menus = menuService.selectMenuByCondition(qmc);
        if (menus!=null){
            return Result.ok().data("result",menus);
        }
        return Result.error();
    }
    // 添加菜单数据
    @RequestMapping("/insertMenu")
    public Result insertMenu(@RequestBody Menu menu) {
        int i = menuService.insertMenu(menu);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
    // 根据菜单ID删除菜单信息
    @RequestMapping("/deleteMenuById")
    public Result deleteMenuById(Integer menuId) {
        int i = menuService.deleteMenuById(menuId);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
    // 根据菜单ID更新菜单信息
    @RequestMapping("/updateMenuById")
    public Result updateMenuById(@RequestBody Menu menu) {
        int i = menuService.updateMenuById(menu);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }

}
