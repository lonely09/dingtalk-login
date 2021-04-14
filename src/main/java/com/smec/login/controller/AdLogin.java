package com.smec.login.controller;

import com.smec.login.config.AuthTokenService;
import com.smec.login.config.ResponseResult;
import com.smec.login.pojo.User;
import com.smec.login.service.UserService;
import com.smec.login.util.TokenUtill;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * adfs 登陆
 * @author 刘鸿亮
 */
@RestController
public class AdLogin {

    private final AuthTokenService authTokenService;
    private final UserService userService;

    public AdLogin(AuthTokenService authTokenService, UserService userService) {
        this.authTokenService = authTokenService;
        this.userService = userService;
    }


    /**
     * AD登陆接口
     * @param token adfs签发的token
     * @return 新token
     */
    @GetMapping("/ad/oauth/token/login")
    public ResponseResult adLogin(@RequestHeader("Authorization") String token){
        Map<String,Object> map;
        try {
            map=authTokenService.adVerifyToke(token);
        }catch (Exception e) {
            return new ResponseResult("token异常",e.getMessage());
        }
        // 根据ad用户工号查询数据
        User user= userService.findByAdEmployeeId(map.get("employeeId").toString());
        // 数据不为空 代表用户已在平台登陆过
        if(null!=user){
            // 签发token
            return new ResponseResult(null,TokenUtill.getToken(user));
        }
        // 用户未在平台登陆 增加一条记录
        User user1=userService.saveUser(new User(null,null,false,map.get("employeeId").toString(),map.get("employeeName").toString()));
        return new ResponseResult(null,TokenUtill.getToken(user1));
    }

}
