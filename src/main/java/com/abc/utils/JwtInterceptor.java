package com.abc.utils;

import cn.hutool.core.util.StrUtil;
import com.abc.bean.User;
import com.abc.exception.CustomException;
import com.abc.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

@CrossOrigin
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        //1.从http请求的header中获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            //如果没拿到，我再去参数里面拿试一试 /api/user?token=xxx
            token = request.getParameter("token");
        }
        System.out.println("token:"+token);
        //2.开始执行认证
        if (StrUtil.isBlank(token) || token.equals("undefined")){
            String errMsg = "无效的token";
            log.error(errMsg +",token=" + token);
            throw new CustomException("无效的token，请重新登录");
        }
        //获取 token中的userId
        String userId;
        User user;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            //根据token中的userid查询数据库
            user = userService.findById(Integer.parseInt(userId));
        } catch (Exception e) {
            String errMsg = "token验证失败1，请重新登录";
            log.error(errMsg +",token=" + token, e);
            throw new CustomException(errMsg);
        }
        if (user == null) {
            throw new CustomException("用户不存在，请重新登录");
        }
        try {
            //用户密码加验证token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token);//验证token

        } catch (JWTVerificationException e) {

            throw new CustomException("token验证失败，请重新登录");
        }
        log.info("用户{}认证通过",user.getUsername());
        return true;
    }
}
