package com.abc.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.abc.bean.User;
import com.abc.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
public class JwtTokenUtils {
    private static UserService staticUserService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Resource
    private UserService userService;
    //项目启动的时候启动该方法（PostConstruct注解的作用）
    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }
    /**
     * 生成Token
     */
    public static String genToken(String userId,String password) {

        return JWT.create().withAudience(userId)//将userid保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))//2小时后token过期
                .sign(Algorithm.HMAC256(password));//以password作为token的密钥
    }
    /**
     * 获取当前登录的用户信息
     */
     public static User getCurrentUser() {
         String token = null;
         try{
             HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

             token = request.getHeader("token");
             System.out.println(token);
             if (StrUtil.isBlank(token)) {
                 token = request.getParameter("token");
             }
             if (StrUtil.isBlank(token)) {
                 log.error("获取当前登录的token失败，token：{}",token);
                 return null;
             }
             //解析token，获取用户的id
             String userId = JWT.decode(token).getAudience().get(0);
             return staticUserService.findById(Integer.valueOf(userId));
         }catch (Exception e){
             log.error("获取当前登录的用户信息失败，token：{}",token,e);

             return null;
         }
     }
}
