package com.yun.common.security.interceptor;

import cn.hutool.core.util.StrUtil;
import com.yun.common.core.constants.HttpConstants;
import com.yun.common.security.service.TokenService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author yun
 * @date 2024/11/3 18:38
 * @desciption: 用于在网关之后执行controller方法之前
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    // 这里读取的配置
    // 哪里的接口用到了这里的拦截器就要用哪里的配置文件
    // 比如说oj-system模块用到了拦截器 那么就读取该模块的配置文件
    // 拦截器是一个bean 交给哪个微服务的容器就读取哪个容器的配置文件
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = getToken(request);
        if (StrUtil.isEmpty(token)) {
            return true;
        }
        tokenService.extendToken(token, secret);
        return true;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(HttpConstants.AUTHENTICATION);
        if (StringUtils.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.replaceFirst(HttpConstants.PREFIX, "");
        }
        return token;
    }
}
