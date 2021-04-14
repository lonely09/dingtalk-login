package com.smec.login.pojo;

import com.smec.login.util.StatusUtil;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "USER_INFO")
@Where(clause = StatusUtil.NOT_DELETE)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    /**
     * 钉钉用户id
     */
    @Column(name="DING_TALK_UNION_ID",unique = true)
    private String dingTalkUnionid;

    /**
     * 微信用户ID
     */
    @Column(name = "WEICHAT_UNION_ID",unique = true)
    private String weiChatUnionid;

    /**
     * 是否已删除
     */
    @Column(name = "IS_DELETED")
    private boolean deleted;

    /**
     * AD 用户工号
     */
    @Column(name = "AD_EMPLOYEE_ID")
    private String adEmployeeId;

    /**
     *  AD 用户姓名
     */
    @Column(name = "AD_EMPLOYEE_NAME")
    private String adEmployeeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDingTalkUnionid() {
        return dingTalkUnionid;
    }

    public void setDingTalkUnionid(String dingTalkUnionid) {
        this.dingTalkUnionid = dingTalkUnionid;
    }

    public String getWeiChatUnionid() {
        return weiChatUnionid;
    }

    public void setWeiChatUnionid(String weiChatUnionid) {
        this.weiChatUnionid = weiChatUnionid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getAdEmployeeId() {
        return adEmployeeId;
    }

    public void setAdEmployeeId(String adEmployeeId) {
        this.adEmployeeId = adEmployeeId;
    }

    public String getAdEmployeeName() {
        return adEmployeeName;
    }

    public void setAdEmployeeName(String adEmployeeName) {
        this.adEmployeeName = adEmployeeName;
    }


    public User(String dingTalkUnionid, String weiChatUnionid, boolean deleted, String adEmployeeId, String adEmployeeName) {
        this.dingTalkUnionid = dingTalkUnionid;
        this.weiChatUnionid = weiChatUnionid;
        this.deleted = deleted;
        this.adEmployeeId = adEmployeeId;
        this.adEmployeeName = adEmployeeName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"dingTalkUnionid\":\"")
                .append(dingTalkUnionid).append('\"');
        sb.append(",\"weiChatUnionid\":\"")
                .append(weiChatUnionid).append('\"');
        sb.append(",\"deleted\":")
                .append(deleted);
        sb.append(",\"adEmployeeId\":\"")
                .append(adEmployeeId).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public User() {
    }
}
