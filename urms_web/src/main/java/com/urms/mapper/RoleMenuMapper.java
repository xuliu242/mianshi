package com.urms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.urms.entity.Menu;
import com.urms.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    //根据角色权限id查找角色权限信息
    @Select("select * from TB_ROLE_MENU where ROLE_MENU_ID=#{roleMenuId}")
    RoleMenu selectByRoleMenuId(Integer roleMenuId);

    //添加角色权限数据
    int insertRoleMenu(RoleMenu roleMenu);

    //根据角色权限ID删除角色权限信息
    @Delete("delete from TB_ROLE_MENU where ROLE_MENU_ID=#{roleMenuId}")
    int deleteRoleMenuById(Integer roleMenuId);

    //根据角色ID删除角色权限信息
    @Delete("delete from TB_ROLE_MENU where ROLE_ID=#{roleId}")
    int deleteRoleMenuByRoleId(Integer roleId);

    //根据角色id查找权限信息
    @Select("SELECT * from TB_MENU where MENU_ID in \n" +
            "(SELECT MENU_ID FROM TB_ROLE_MENU where ROLE_ID = #{roleId})\n")
    List<Menu> selectByRoleId(Integer roleId);

    //根据用户id 查询菜单列表
    @Select("SELECT * FROM TB_MENU where MENU_ID in \n" +
            "(select DISTINCT MENU_ID from TB_ROLE_MENU rm where rm.ROLE_ID in \n" +
            "(select ur.ROLE_ID from TB_USER_ROLE ur where ur.USER_ID = #{userId})\n" +
            ")ORDER BY  MENU_ID")
    List<Menu> selectMenuByUserId(Integer userId);

}
