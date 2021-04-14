package com.smec.login.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.smec.login.dto.TokenResponse;
import com.smec.login.pojo.User;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *  签发token的工具
 * @author 刘鸿亮
 * @date 2021/03/31
 */

public class TokenUtill {


    /**
     * 生成token
     * @param user 用户对象
     * @return 响应信息
     */
    public static TokenResponse getToken(User user){
        Map<String,String> map =new HashMap<>();

        if (!StringUtils.isEmpty(user.getDingTalkUnionid())){
            map.put("ding_talk_unionId",user.getDingTalkUnionid());
        }

        if(!StringUtils.isEmpty(user.getWeiChatUnionid())){
            map.put("weiChatUnionId",user.getWeiChatUnionid());
        }

        if(!StringUtils.isEmpty(user.getAdEmployeeId())){
            map.put("adEmployeeId",user.getAdEmployeeId());
        }

        if(!StringUtils.isEmpty(user.getAdEmployeeName())){
            map.put("adEmployeeName",user.getAdEmployeeName());
        }

        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,120);
        builder.withExpiresAt(instance.getTime());

        return new TokenResponse(builder.sign(Algorithm.HMAC256("token*7@gl12%")).toString(), "bearer", null);
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    private static void verify(String token){
        JWT.require(Algorithm.HMAC256("token*7@gl12%")).build().verify(token);
    }

    /**
     *  验证token并返回验证后的结果
     * @param token access_token
     * @return
     */
    public static Map<String,Object> verifyToken(String token){
        Map<String, Object> map = new HashMap<>();
        try {
            verify(token);
            map.put("msg", "验证通过~~~");
            map.put("state", true);
        } catch (TokenExpiredException e) {
            map.put("state", false);
            map.put("msg", "Token已经过期!!!");
        } catch (SignatureVerificationException e){
            map.put("state", false);
            map.put("msg", "签名错误!!!");
        } catch (AlgorithmMismatchException e){
            map.put("state", false);
            map.put("msg", "加密算法不匹配!!!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "无效token~~");
        }
        return map;
    }
}
