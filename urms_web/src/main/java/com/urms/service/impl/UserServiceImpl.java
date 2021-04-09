package com.urms.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.urms.entity.QueryUserCondition;
import com.urms.entity.User;
import com.urms.mapper.UserMapper;
import com.urms.service.UserService;
import com.urms.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lx
 * @since 2021-03-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public IPage<User> queryAllStudentByPage(Page page) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectByUserId(Integer userId) {
        return null;
    }

    @Override
    public User selectUserByLoginName(String userLoginName) {
        return userMapper.selectUserByLoginName(userLoginName);
    }

    @Override
    public int insertUser(User user) {
        String salt = String.valueOf(user.getUserLoginName());
        String md5Encryption = MD5Utils.md5Encryption(user.getUserPassword(), salt);
        String substring = md5Encryption.substring(8, 24);
        user.setUserPassword(substring);

        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUserById(Integer userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public int updateUserStatusById(Integer userId, String userStatus) {
        System.out.println("userId"+userId+",userStatus"+userStatus);
        return userMapper.updateUserStatusById(userId,userStatus);
    }

    @Override
    public List<User> selectUserByCondition(QueryUserCondition queryUserCondition) {
        List<User> userList = userMapper.selectUserByCondition(queryUserCondition);
        return userList;
    }


}
