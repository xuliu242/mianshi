package com.urms.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.urms.entity.QueryUserCondition;
import com.urms.entity.User;
import com.urms.response.Result;
import com.urms.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //    根据用户id查找用户信息
    @RequestMapping("/selectByUserId")
    public Result selectByUserId(Integer userId) {
        User user = userService.selectByUserId(userId);
        if (user!=null){
            return Result.ok().data("result",user);
        }
        return Result.error().message("无此用户");
    }

    //    根据用户名查找用户信息
    @RequestMapping("/selectByLoginName")
    public Result selectByLoginName(String userLoginName) {
        User user = userService.selectUserByLoginName(userLoginName);
        if (user!=null){
            return Result.ok().data("result",user);
        }
        return Result.error().message("无此用户");

    }

       // 查询所有用户信息
    @RequestMapping("/selectAll")
    @ResponseBody
    public Result selectAll() {
        PageHelper.startPage(1,4);
//        List<User> userList = userService.selectAll();
        PageInfo pageInfo=new PageInfo(userService.selectAll());
        return Result.ok().data("userList", pageInfo.getList()).data("total",pageInfo.getTotal());
    }

    //    条件查询
    @RequestMapping("/selectUserByCondition")
    @ResponseBody
    public Result selectUserByCondition(@RequestBody QueryUserCondition quc) {
//       获取分页数据
        Integer pageNum = quc.getPageNum()==null?1:quc.getPageNum();
        Integer pageSize = quc.getPageSize()==null?10:quc.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
//        获取条件
        List<User> result=new ArrayList<>();
        PageInfo pageInfo=new PageInfo(userService.selectUserByCondition(quc));


        return Result.ok().data("result",pageInfo.getList()).data("total",pageInfo.getTotal()).data("pages",pageInfo.getPages());
    }

    //    添加用户数据
    @RequestMapping("/insertUser")
    @RequiresPermissions("user:add")
    @ResponseBody
    public Result insertUser(@RequestBody User user) {
        user.setUserCreateTime(new Date());
        int i = userService.insertUser(user);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    //    根据用户ID删除用户信息
    @RequestMapping("/deleteUserById")
    @ResponseBody
    @RequiresPermissions({"user:delete"})
    public Result deleteUserById(Integer userId) {
        int i = userService.deleteUserById(userId);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    //    根据用户ID更新用户信息
    @RequestMapping("/updateUserById")
    @RequiresPermissions("user:update")
    @ResponseBody
    public Result updateUserById(@RequestBody User user) {
        user.setUserUpdateTime(new Date());
        int i = userService.updateUserById(user);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }

    //    根据用户ID更新用户状态
    @RequestMapping("/updateUserStatusById")
    @RequiresPermissions("user:update")
    @ResponseBody
    public Result updateUserStatusById(@RequestBody Map<String,Object> map) {
        Integer userId = (Integer) map.get("userId");
        String userStatus = (String) map.get("userStatus");
        int i = userService.updateUserStatusById(userId, userStatus);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
    @RequestMapping("/deleteUserByIdMultiple")
    @ResponseBody
    @RequiresPermissions("user:batch_delete")
    public Result deleteUserByIdMultiple(@RequestBody Map<String,Object> map) {
        List<Object> list = (List<Object>) map.get("userList");
        for (Object o:list) {
            String s = JSON.toJSONString(o);
            User user = JSONObject.parseObject(s, User.class);
            int i = userService.deleteUserById(user.getUserId());
            if (i <= 0) {
                return Result.error();
            }
        }
        return Result.ok();
    }

}
