package com.smec.login.config;
import com.alibaba.druid.util.StringUtils;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: xiaowei.yao
 * @since: 2020/3/21 10:50 AM
 * @history: 1.2020/3/21 created by xiaowei.yao
 */
@Service
public class AuthTokenService {
    private static final Logger log = LoggerFactory.getLogger(AuthTokenService.class);

    @Autowired
    @Qualifier("jwtTokenProcessor")
    ConfigurableJWTProcessor jwtProcessor;

    /**
     *  三菱adfs验证token
     * @param token
     * @return
     */
    public Map<String,Object> adVerifyToke(String token) throws Exception{
        if (StringUtils.isEmpty(token)) {
            throw new Exception("请携带token");
        }
        //处理收到的token（令牌),错误则返回对象
        SecurityContext ctx = null;
        JWTClaimsSet claimsSet = null;
        try {
            claimsSet = jwtProcessor.process(token, ctx);
        } catch (Exception e) {
            log.error(e.toString());
            throw new Exception("请携带token");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("employeeId", getUid(claimsSet.getClaims()));
        map.put("employeeName", getUserName(claimsSet.getClaims()));
        return map;
    }

    /**
     *  获取三菱用户工号
     * @param tokenMap
     * @return
     */
    public String getUid(Map<String,Object> tokenMap) throws Exception{
        log.info("=====截取upn中的工号=========");
        String upn = (String) Optional.ofNullable(tokenMap.get("upn")).orElseThrow(() -> new Exception("token获取工号异常"));
        return upn.replace("@smec-cn.com", "");
    }

    /**
     *  获取三菱用户名称
     * @param tokenMap token map
     * @return 用户名字
     */
    public String getUserName(Map<String,Object> tokenMap) {
        return (String) tokenMap.get("unique_name");
    }


}

