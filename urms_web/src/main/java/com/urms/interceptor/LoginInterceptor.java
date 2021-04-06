//package com.urms.interceptor;
//
//import com.alibaba.druid.util.StringUtils;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.urms.entity.User;
//import com.urms.handle.BusinessException;
//import com.urms.response.ResultCode;
//import com.urms.service.UserService;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
//public class LoginInterceptor implements HandlerInterceptor {
//
//    private UserService userService;
//
//    public LoginInterceptor(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 从 http 请求头中取出 token
//        String token = request.getHeader("token");
//        if (StringUtils.isEmpty(token)) {
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    String name = cookie.getName();
//                    if ("x-token".equals(name)) {
//                        token = cookie.getValue();
//                    }
//                }
//            }
//        }
//
//
//        // 如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(CheckToken.class)) {
//            CheckToken checkToken = method.getAnnotation(CheckToken.class);
//            if (checkToken.required()) {
//                // 执行认证
//                if (StringUtils.isEmpty(token)) {
//                    throw new RuntimeException("无token，请重新登录");
//                }
//                // 获取 token 中的 user id
//                String userId;
//                try {
//                    userId = JWT.decode(token).getClaim("id").asString();
//                } catch (JWTDecodeException j) {
//                    throw new RuntimeException("访问异常！");
//                }
//                User user = userService.getById(userId);
//                if (user == null) {
//                    throw new BusinessException(ResultCode.USER_NOT_FOUND_EXCEPTION.getCode(),
//                            ResultCode.USER_NOT_FOUND_EXCEPTION.getMessage());
//
//                }
//                Boolean verify = JwtUtils.isVerify(token, user);
//                if (!verify) {
//                    throw new BusinessException(ResultCode.ACCESS_EXCEPTION.getCode(),
//                            ResultCode.ACCESS_EXCEPTION.getMessage());
//                }
//                return true;
//            }
//        }
//        return true;
//    }
//}
