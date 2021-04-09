package com.urms.controller.login;

import com.urms.entity.User;
import com.urms.handle.BusinessException;
import com.urms.interceptor.JWTToken;
import com.urms.response.Result;
import com.urms.service.UserService;
import com.urms.utils.JWTUtils;
import com.urms.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
        User userLogin = userService.selectUserByLoginName(user.getUserLoginName());
        if (userLogin == null) {
            return Result.error().message("无此用户");
        }
        String md5Encryption = MD5Utils.md5Encryption(user.getUserPassword(), userLogin.getUserLoginName());
        String substring = md5Encryption.substring(8, 24);
        String token= JWTUtils.sign(user.getUserLoginName(),substring);
        JWTToken jwtToken=new JWTToken(token);
        try {
            SecurityUtils.getSubject().login(jwtToken);
        } catch (AuthenticationException e) {
            throw new BusinessException(2001,e.getMessage());
        }

        return Result.ok().data("userId",userLogin.getUserId()).data("token",token);
    }
    @RequestMapping("/unauthorized")
    @ResponseBody
    public Result unauthorized(){
        return Result.error().message("非法请求");
    }

}
