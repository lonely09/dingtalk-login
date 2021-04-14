package com.smec.login.dao;

import com.smec.login.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer>{

    User findByDingTalkUnionid(String dingTalkUnionId);

    User findByWeiChatUnionid(String weiChatUnionId);

    User findByAdEmployeeId(String adEmployeeId);
}
