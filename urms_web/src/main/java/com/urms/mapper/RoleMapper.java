package com.urms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.urms.entity.QueryRoleCondition;
import com.urms.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    //根据角色id查找角色信息
    @Select("select * from TB_ROLE where ROLE_ID=#{roleId}")
    Role selectByRoleId(Integer roleId);

    //根据角色名查找角色信息
    @Select("select * from TB_ROLE where ROLE_NAME=#{roleName}")
    Role selectByRoleName(String roleName);

    //条件查询
    List<Role> selectRoleByCondition(QueryRoleCondition qrc);

    //添加角色数据

    int insertRole(Role role);

    //根据角色ID删除角色信息
    @Delete("delete from TB_ROLE where ROLE_ID=#{roleId}")
    int deleteRoleById(Integer roleId);

    //根据角色ID更新角色信息
    int updateRoleById(Role role);
//    //根据角色id查找角色信息
//    int selectByRoleId(Role role);



}
