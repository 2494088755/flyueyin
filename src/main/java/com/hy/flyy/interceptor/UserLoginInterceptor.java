package com.hy.flyy.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hy.flyy.entity.User;
import com.hy.flyy.enums.ResponseCodeEnums;
import com.hy.flyy.exception.ServerException;
import com.hy.flyy.service.UserService;
import com.hy.flyy.utils.JwtUtils;
import com.hy.flyy.utils.R;
import com.hy.flyy.utils.RedisUtils;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 黄勇
 * @since 2023/4/26
 */
@Slf4j
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        if ("/error".equals(request.getRequestURI())) {
            throw new ServerException("服务器异常");
        }

        log.info("拦截到请求:" + request.getRequestURI());

        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            log.info("用户未认证");
            return unauthenticatedReturn(response);
        }
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = JSON.parseObject(redisUtils.getCacheObject("user_" + username).toString(), User.class);
        if (StrUtil.isBlank(token) || !Objects.equals(user.getUsername(), username)) {
            log.info("用户未认证");
            return unauthenticatedReturn(response);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    public boolean unauthenticatedReturn(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new R<>(ResponseCodeEnums.UNAUTHORIZED)));
        return false;
    }
}
