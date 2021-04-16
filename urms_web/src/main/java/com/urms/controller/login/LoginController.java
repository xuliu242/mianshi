package com.urms.controller.login;

import com.urms.entity.User;
import com.urms.handle.BusinessException;
import com.urms.interceptor.JWTToken;
import com.urms.response.Result;
import com.urms.service.UserService;
import com.urms.utils.JWTUtils;
import com.urms.utils.MD5Utils;
import com.urms.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(tags = "登录模块")
@Controller
//@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    RedisUtil redisUtil;

    //    public
    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(@RequestBody User user, HttpServletResponse response) {
        User userLogin = userService.selectUserByLoginName(user.getUserLoginName());
        if (userLogin == null) {
            return Result.error().message("无此用户");
        }
        String md5Encryption = MD5Utils.md5Encryption(user.getUserPassword(), user.getUserLoginName());
        String substring = md5Encryption.substring(8, 24);
        if (!substring.equals(userLogin.getUserPassword())){
            return Result.error().message("密码错误");
        }
        Long currentTimeMillis = System.currentTimeMillis();
        String token = JWTUtils.sign(user.getUserLoginName(),currentTimeMillis);
//        JWTToken jwtToken = new JWTToken(token);
//        try {
//            SecurityUtils.getSubject().login(jwtToken);
//        } catch (AuthenticationException e) {
//            throw new BusinessException(2001, e.getMessage());
//        }
        redisUtil.set(userLogin.getUserLoginName(),currentTimeMillis,JWTUtils.REFRESH_EXPIRE_TIME);
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Result.ok().data("userId", userLogin.getUserId()).data("token", token);
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public Result unauthorized() {
        return Result.error().message("非法请求");
    }

    @RequestMapping("/token-error")
    @ResponseBody
    public Result token() {
        return Result.error().code(501).message("token错误");
    }

}
