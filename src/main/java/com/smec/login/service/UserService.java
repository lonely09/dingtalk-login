package com.smec.login.service;


import com.smec.login.pojo.User;

public interface UserService {
    User findByDingTalkUnionId(String dingTalkUnionId);

    User findByWeiChatUnionId(String weiChatUnionId);

    User saveUser(User user);

    User findByAdEmployeeId(String adEmployeeId);
}
