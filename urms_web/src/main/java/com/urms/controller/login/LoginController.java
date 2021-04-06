package com.urms.controller.login;

import com.urms.entity.User;
import com.urms.response.Result;
import com.urms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

//    public
    @RequestMapping("/doLogin")
    @ResponseBody
    public Result doLogin(@RequestBody User user, HttpServletResponse response, HttpSession session){
        User userByLoginName = userService.selectUserByLoginName(user.getUserLoginName());
        if (userByLoginName == null) {
            return Result.error().message("无此用户");
        }
        if (userByLoginName.getUserPassword().equals(user.getUserPassword())){
            session.setAttribute("UserLoginName",user.getUserLoginName());
            return Result.ok().data("userId",userByLoginName.getUserId());
        }
        return Result.error().message("账号或密码错误");
    }

}
