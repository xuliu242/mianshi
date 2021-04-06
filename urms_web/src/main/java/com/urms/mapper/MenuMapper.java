package com.urms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.urms.entity.Menu;
import com.urms.entity.QueryMenuCondition;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    //  根据菜单id查找菜单信息
    @Select("select * from TB_MENU where MENU_ID=#{menuId}")
    Menu selectByMenuId(Integer menuId);

    //  根据菜单名查找菜单信息
    @Select("select * from TB_MENU where MENU_NAME=#{menuName}")
    Menu selectByMenuName(String menuName);

    //  条件查询
    List<Menu> selectMenuByCondition(QueryMenuCondition qmc);

    //  添加菜单数据
    int insertMenu(Menu menu);

    //  根据菜单ID删除菜单信息
    @Delete("delete from TB_MENU where MENU_ID=#{menuId}")
    int deleteMenuById(Integer menuId);

    //  根据菜单ID更新菜单信息
    int updateMenuById(Menu menu);


}
