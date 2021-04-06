package com.urms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.urms.entity.QueryUserCondition;
import com.urms.entity.User;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
public interface UserService extends IService<User> {
    public IPage<User> queryAllStudentByPage(Page page);
    // 查询所有用户信息
    List<User> selectAll();
    // 根据用户ID查询用户信息
    User selectByUserId(Integer userId);
    // 通过用户名查询用户信息
    User selectUserByLoginName(String userLoginName);
    // 添加用户数据
    int insertUser(User user);
    // 根据用户ID删除用户信息
    int deleteUserById(Integer userId);
    // 根据用户ID更新用户信息
    int updateUserById(User user);
    // 根据用户ID更新用户状态
    int updateUserStatusById(Integer userId,String userStatus);
    // 条件查询
    List<User> selectUserByCondition(QueryUserCondition queryUserCondition);
}
