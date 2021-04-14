package com.smec.login.service.impl;

import com.smec.login.dao.UserDao;
import com.smec.login.dto.TokenResponse;
import com.smec.login.pojo.User;
import com.smec.login.service.UserService;
import com.smec.login.util.TokenUtill;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import sun.tools.jstat.Token;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByDingTalkUnionId(String dingTalkUnionId) {
        return userDao.findByDingTalkUnionid(dingTalkUnionId);
    }

    @Override
    public User findByWeiChatUnionId(String weiChatUnionId) {
        return userDao.findByWeiChatUnionid(weiChatUnionId);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User findByAdEmployeeId(String adEmployeeId) {
        return userDao.findByAdEmployeeId(adEmployeeId);
    }
}
