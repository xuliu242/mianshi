package com.urms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.urms.entity.Menu;
import com.urms.entity.QueryMenuCondition;
import com.urms.mapper.MenuMapper;
import com.urms.service.MenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectMenuByCondition(null);
    }

    @Override
    public Menu selectByMenuId(Integer menuId) {
        return menuMapper.selectByMenuId(menuId);
    }

    @Override
    public Menu selectMenuByMenuName(String menuName) {
        return menuMapper.selectByMenuName(menuName);
    }

    @Override
    public int insertMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    public int deleteMenuById(Integer menuId) {
        return menuMapper.deleteMenuById(menuId);
    }

    @Override
    public int updateMenuById(Menu menu) {
        return menuMapper.updateMenuById(menu);
    }

    @Override
    public List<Menu> selectMenuByCondition(QueryMenuCondition qmc) {

        List<Menu> menusAll = menuMapper.selectMenuByCondition(qmc);//  全部菜单
        List<Menu> firstMenuList=new ArrayList<>();
//        List<Menu> secMenuList=new ArrayList<>();
//        List<Menu> thirdMenuList=new ArrayList<>();
        //筛选第一级目录菜单
        for (Menu menu:menusAll) {
            if (menu.getMenuParentId()==null){
                firstMenuList.add(menu);
            }
        }
        //筛选第二级目录
        for (Menu menu: firstMenuList)
        {
            List<Menu> menuList = menuMapper.selectByParentId(menu.getMenuId());
            menu.setChildren(menuList);
        }
        //获取第三级目录
        for(Menu menu: firstMenuList){
            for (Menu secMenu:menu.getChildren()){
                List<Menu> menuList = menuMapper.selectByParentId(secMenu.getMenuId());
                secMenu.setChildren(menuList);
            }
        }
        return  firstMenuList;
    }

    @Override
    public List<Menu> selectByParentId(Integer menuParentId) {
        return menuMapper.selectByParentId(menuParentId);
    }
}
