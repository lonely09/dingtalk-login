package com.smec.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.Objects;

/**
 * 全局统一返回类处理
 *
 * @author 刘鸿亮
 */
@Configuration
@EnableWebMvc
@RestControllerAdvice
public class GlobalReturnConfig extends ResponseEntityExceptionHandler implements ResponseBodyAdvice<Object>  {

    private final static String SUCCESS_BIZ_CODE = "200";
    private final static String ERROR_BIZ_CODE = "500";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ResponseResult) {
            return o;
        }
        // 如果是 get 请求 并且 获取的资源为 null ,则响应的状态码为 404
        if (Objects.isNull(o) && Objects.equals(serverHttpRequest.getMethod(), HttpMethod.GET)){
            serverHttpResponse.setStatusCode(HttpStatus.NOT_FOUND);
        }
        String name = methodParameter.getMethod().getName();
        if ("error".equals(name)){
            return new ResponseResult(ERROR_BIZ_CODE,false,name,o);
        }

        return new ResponseResult(SUCCESS_BIZ_CODE,true,"",o);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(
                new ResponseResult(ERROR_BIZ_CODE,false,ex.getMessage(),""),
                headers,
                status
        );

    }
}
