package com.smec.login.config;


import com.smec.login.util.TokenUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: wenbo.liao
 * @since: 2020/8/26 9:21
 * @history: 1.2020/8/26 created by wenbo.liao
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {
    private final AuthTokenService authTokenService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GlobalInterceptor(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        String tokenString = request.getHeader("Authorization");
        log.info("=========获取请求头中的token信息============={}",tokenString);
        if(StringUtils.isEmpty(tokenString)){
            throw new Exception("请携带token");
        }
        TokenUtill.verifyToken(tokenString.replaceFirst("Bearer ", ""));
        return true;
    }
}
