package com.urms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.urms.entity.Menu;
import com.urms.entity.QueryMenuCondition;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
public interface MenuService extends IService<Menu> {
    // 查询所有菜单信息
    List<Menu> selectAll();
    // 根据菜单ID查询菜单信息
    Menu selectByMenuId(Integer menuId);
    // 通过菜单名查询菜单信息
    Menu selectMenuByMenuName(String menuName);
    // 添加菜单数据
    int insertMenu(Menu menu);
    // 根据菜单ID删除菜单信息
    int deleteMenuById(Integer menuId);
    // 根据菜单ID更新菜单信息
    int updateMenuById(Menu menu);
    // 条件查询
    List<Menu> selectMenuByCondition(QueryMenuCondition qmc);

}
