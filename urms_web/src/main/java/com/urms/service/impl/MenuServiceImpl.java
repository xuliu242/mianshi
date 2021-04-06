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
        List<Menu> menusAll = menuMapper.selectMenuByCondition(qmc);
        List<Menu> menuFather = new ArrayList<>();
        List<Menu> menuChildren = new ArrayList<>();
        for (int i = 0; i < menusAll.size(); i++) {
            if (menusAll.get(i).getMenuParentId() == null) {
                menuFather.add(menusAll.get(i));
            } else
                menuChildren.add(menusAll.get(i));

        }
        for (Menu menu : menuFather) {
            List<Menu> menuList=new ArrayList<>();
            for (Menu menuChild: menuChildren) {
                if (menu.getMenuId().equals(menuChild.getMenuParentId())){
                    menuList.add(menuChild);
                }
            }
            menu.setChildren(menuList);

        }
        return menuFather;
    }
}
