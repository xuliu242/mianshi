package com.urms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.urms.entity.QueryUserRoleCondition;
import com.urms.entity.UserRole;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {
    // 根据用户所属角色id查找用户所属角色信息
    @Select("select ur.USER_ROLE_ID,ur.USER_LOGIN_NAME,ur.ROLE_ID,r.ROLE_NAME,ur.USER_ID,u.USER_NAME \n" +
            "from TB_USER_ROLE ur\n" +
            "LEFT JOIN TB_ROLE r on ur.ROLE_ID=r.ROLE_ID\n" +
            "LEFT JOIN TB_USER u on ur.USER_ID=u.USER_ID\n" +
            "where USER_ROLE_ID=#{userRoleId}")
    UserRole selectByUserRoleId(Integer userRoleId);

    // 根据用户所属角色名查找用户所属角色信息 ???
    List<UserRole> selectByUserRoleName(String userRoleName);

    // 条件查询
    List<UserRole> selectUserRoleByCondition(QueryUserRoleCondition qurc);

    // 根据用户所属角色id查找用户所属角色信息
    int insertUserRole(UserRole userRole);

    // 根据用户所属角色id查找用户所属角色信息
    @Delete("delete from TB_USER_ROLE\n" +
            "    where USER_ROLE_ID = #{userRoleId}")
    int deleteUserRoleById(Integer userRoleId);

    // 根据用户所属角色ID更新用户所属角色信息
    int updateUserRoleById(UserRole userRole);

    // 根据用户id查询角色ids
    @Select("SELECT ROLE_ID FROM TB_USER_ROLE where USER_ID=#{userId} ORDER BY ROLE_ID")
    List<Integer> selectRoleIds(Integer userId);

    // 根据用户id删除信息
    @Delete("delete from TB_USER_ROLE where USER_ID=#{userId}")
    int deleteByUserId(Integer userId);

}
