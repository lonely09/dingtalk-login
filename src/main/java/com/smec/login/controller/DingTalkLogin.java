package com.smec.login.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.smec.login.dto.TokenResponse;
import com.smec.login.pojo.User;
import com.smec.login.service.UserService;
import com.smec.login.util.TokenUtill;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 钉钉登陆
 * @author 刘鸿亮
 */
@RestController
public class DingTalkLogin {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Value("${appId}")
    private String appid;
    @Value("${appSecret}")
    private String appSecret;

    @Resource
    private UserService userService;

    /**
     * 移动扫码登陆获取用户信息
     * @param tmpAuthCode 前端回调URL的code值
     * @return
     * @throws ApiException
     */
    @GetMapping("/dingtalk/mobile/user_info/{tmp_auth_code}")
    public OapiSnsGetuserinfoBycodeResponse getInfo(@PathVariable("tmp_auth_code") String tmpAuthCode) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(tmpAuthCode);
        OapiSnsGetuserinfoBycodeResponse response = client.execute(req,appid,appSecret);
        return  response;
    }


    /**
     * 扫码登陆，签发token
     * @param tmpAuthCode
     * @return
     * @throws ApiException
     */
    @GetMapping("/dingtalk/mobole/oauth2/{tmp_auth_code}")
    public TokenResponse mobileLoginVerify(@PathVariable("tmp_auth_code") String tmpAuthCode) throws ApiException{
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(tmpAuthCode);
        OapiSnsGetuserinfoBycodeResponse response = client.execute(req,appid,appSecret);
        User user=userService.findByDingTalkUnionId(response.getUserInfo().getUnionid());
        log.info("====查询结果:{}",user.toString());
        if(user!=null){
            return TokenUtill.getToken(user);
        }else{
            User user1=userService.saveUser(new User(response.getUserInfo().getUnionid(),null,false,null,null));
            return TokenUtill.getToken(user1);
        }
    }

    @GetMapping("/oauth2/verify/token")
    public Map<String,Object> verifyToken(@RequestHeader("Authorization") String token){
        log.info("请求token：{}",token);
        return TokenUtill.verifyToken(token);
    }

}
