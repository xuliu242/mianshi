package com.urms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.urms.entity.QueryUserCondition;
import com.urms.entity.User;
import org.apache.ibatis.annotations.*;

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
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from TB_USER ")
    public IPage<User> queryAllUserByPage(Page page);
    //    查询所有用户信息
    public List<User> selectAll();
    //    根据用户ID查询用户信息
    public User selectByUserId(Integer userId);
    //    通过用户名查询用户信息
    public User selectUserByLoginName(String userLoginName);
    //    添加用户数据
    public int insertUser(User user);
    //    根据用户ID删除用户信息
    @Delete("delete from TB_USER where USER_ID=#{userId}")
    public int deleteUserById(Integer userId);
    //    根据用户ID更新用户信息
    public int updateUserById(User user);
    //    根据用户ID更新用户状态
    @Update("update TB_USER set USER_STATUS=#{userStatus} where USER_ID=#{userId}")
    public int updateUserStatusById(@Param("userId") Integer userId, @Param("userStatus")String userStatus);
    public List<User> selectUserByCondition(QueryUserCondition queryUserCondition);
}
