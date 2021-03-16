package com.xy.interceptor;

import com.xy.auth.pojo.UserToken;
import com.xy.auth.service.UserTokenService;
import com.xy.auth.tokenUtil.TokenGenerator;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.exceptionHandle.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器");
        System.out.println("请求路径为："+request.getRequestURI());
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpStatus.OK.value());
            System.out.println("接收到options请求");
            return false;
        }

        String URI = request.getRequestURI();
        if (URI.contains("auth") || URI.contains("get") || URI.contains("questionnaireAnswer/add") || URI.contains("test") || URI.contains(".html")){
            System.out.println("访问了 "+URI+" 接口，放行");
            return true;
        }
        System.out.println("进入拦截器");
        // 放行登录相关的接口
        if (request.getRequestURI().contains("login")){
            System.out.println("登录相关接口，已放行");
            return true;
        }

        String requestToken = request.getHeader("token");

        if (requestToken == null){ throw new ServerException(MsgEnum.TOKEN_NOT_FOUND); }

        UserToken userToken = userTokenService.getUserTokenByToken(requestToken);
        if (userToken == null) {
            throw new ServerException(MsgEnum.TOKEN_NOT_FOUND);
        } else {
            // 在数据库中能够找到匹配的userToken;先检查token是否过期，若过期则要求重新登录
            LocalDateTime expireTime = userToken.getExpireTime();
            if (expireTime == null | LocalDateTime.now().isAfter(expireTime)){
                throw new ServerException(MsgEnum.TOKEN_EXPIRED);
            } else { // 若token未过期，则更新用户的token并放行
                // 若token的expireTime晚于当前时间，则检测token的createTime是否过早，需要强制更新
                LocalDateTime forceExpireTime = userToken.getCreateTime().plusHours(tokenGenerator.getLongestExpiryPeriod());
                if (LocalDateTime.now().isAfter(forceExpireTime)) { // token使用时间过长需要强制更新
                    String newToken = tokenGenerator.generateTokenWithUserID(userToken.getUserID());
                    userToken.setToken(newToken);
                    userToken.setCreateTime(LocalDateTime.now());
                    userToken.setExpireTime(LocalDateTime.now().plusHours(tokenGenerator.getExpriyPeriod()));
                    userTokenService.updateUserToken(userToken);
                    // 将更新后的token放在header里，发给前端
                    response.setHeader("token", newToken);
                    System.out.println("token使用时间过长，已放行并强制更新");
                    return true;
                } else { // 若token使用时间未超出限制，则仅更新expireTime并放行
                    userToken.setExpireTime(LocalDateTime.now().plusHours(tokenGenerator.getExpriyPeriod()));
                    userTokenService.updateUserToken(userToken);
                    System.out.println("更新token过期时间并放行");
                    return true;
                }
            }
        }
    }

}
