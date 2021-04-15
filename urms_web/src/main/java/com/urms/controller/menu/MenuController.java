package com.urms.controller.menu;

import com.urms.entity.Menu;
import com.urms.entity.QueryMenuCondition;
import com.urms.response.Result;
import com.urms.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "菜单管理模块")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    // 根据菜单id查找菜单信息
    @ApiOperation(value = "根据菜单id查找菜单信息")
    @RequiresPermissions("menu:select")
    @RequestMapping(value = "/selectByMenuId",method = RequestMethod.GET)
    public Result selectByMenuId(Integer menuId) {
        Menu menu = menuService.selectByMenuId(menuId);
        if (menu!=null){
            return Result.ok().data("result",menu);
        }
        return Result.error();
    }
    //
    @RequestMapping(value = "/selectByParentId",method = RequestMethod.GET)
    @RequiresPermissions("menu:select")
    public Result selectByParentId(Integer menuId) {
        List<Menu> menu = menuService.selectByParentId(null);
        if (menu!=null){
            return Result.ok().data("result",menu);
        }
        return Result.error();
    }
    // 根据菜单名查找菜单信息
    @ApiOperation(value = "根据菜单名查找菜单信息")
    @RequiresPermissions("menu:select")
    @RequestMapping(value = "/selectByMenuName",method = RequestMethod.GET)
    public Result selectByMenuName(String menuName) {
        Menu menu = menuService.selectMenuByMenuName(menuName);
        if (menu!=null){
            return Result.ok().data("result",menu);
        }
        return Result.error();
    }
    // 条件查询
    @ApiOperation(value = "条件查询")
    @RequiresPermissions("menu:select")
    @RequestMapping(value = "/selectMenuByCondition",method = RequestMethod.POST)
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
    @ApiOperation(value = "添加菜单数据")
    @RequiresPermissions("menu:add")
    @RequestMapping(value = "/insertMenu",method = RequestMethod.POST)
    public Result insertMenu(@RequestBody Menu menu) {
        int i = menuService.insertMenu(menu);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
    // 根据菜单ID删除菜单信息
    @ApiOperation(value = "根据菜单ID删除菜单信息")
    @RequiresPermissions("menu:delete")
    @RequestMapping(value = "/deleteMenuById",method = RequestMethod.GET)
    public Result deleteMenuById(Integer menuId) {
        int i = menuService.deleteMenuById(menuId);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
    // 根据菜单ID更新菜单信息
    @ApiOperation(value = "根据菜单名查找菜单信息")
    @RequiresPermissions("menu:update")
    @RequestMapping(value = "/updateMenuById",method = RequestMethod.POST)
    public Result updateMenuById(@RequestBody Menu menu) {
        int i = menuService.updateMenuById(menu);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }

}
