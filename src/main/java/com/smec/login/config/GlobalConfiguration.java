package com.smec.login.config;


import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: wenbo.liao
 * @since: 2020/8/13 8:07
 * @history: 1.2020/8/13 created by wenbo.liao
 */
@Configuration
public class GlobalConfiguration implements WebMvcConfigurer {
    @Autowired
    GlobalInterceptor globalInterceptor;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 设置不被拦截的请求路径
     * 有全局异常必须将springboot自带的error路径放开，否则会出现循环拦截
     */
    private static final List<String> EXCLUDE_PATH_LIST = Arrays.asList(
            "/error","/push/system","/ar-account-details/batch-insert","/dingtalk/mobole/oauth2/*"
    );

    /**
     * 拦截器设置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //全局token拦截器配置
        InterceptorRegistration registration = registry.addInterceptor(globalInterceptor);
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(EXCLUDE_PATH_LIST);
    }

    /**
     * 由于拦截器在CorsRegistry之前执行，会导致CorsRegistry设置的跨域失效所以改用filter设置跨域。
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域请求的域名
        config.addAllowedOrigin("*");
        // 是否允许证书 不再默认开启
        config.setAllowCredentials(true);
        // 设置允许的方法
        config.addAllowedMethod("*");
        // 允许任何头
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    /**
     * jwt解析对象 解析三菱ADFS
     * @return
     */
    @Bean(name = "jwtTokenProcessor")
    public ConfigurableJWTProcessor jwtProcessor(){
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
        // 校验ADFS的token有效性
        String jwkEndpoint = "https://adfs.smec-cn.com/adfs/discovery/keys";
        //提供公钥地址来获取
        JWKSource keySource = null;
        try {
            keySource = new RemoteJWKSet(new URL(jwkEndpoint));
        } catch (MalformedURLException e) {
            log.info("获取keySource异常："+e.getMessage());
        }
        //提供解析算法，算法类型要写对，服务器用的是什么就是什么，目前是RSA256算法
        JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;
        //填写 RSA 公钥来源从提供公钥地址获取那边得到
        JWSKeySelector keySelector = new JWSVerificationKeySelector(expectedJWSAlg, keySource);
        //设置第一步建立的解析处理对象
        jwtProcessor.setJWSKeySelector(keySelector);
        return jwtProcessor;
    }
}
