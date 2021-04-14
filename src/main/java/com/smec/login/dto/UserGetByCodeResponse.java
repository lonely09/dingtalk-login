package com.smec.login.dto;

/**
 * 钉钉返回的用户信息
 * @author 刘鸿亮
 */
public class UserGetByCodeResponse {
    // 用户的userId
    private String userid;

    // 设备ID
    private String device_id;

    // 是否是管理员
    private Boolean sys;

    // 级别
    private Number sys_level;

    // 用户关联的unionid
    private String associated_unionid;

    // 用户的unionid
    private String unionid;

    //用户名字
    private String name;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Boolean getSys() {
        return sys;
    }

    public void setSys(Boolean sys) {
        this.sys = sys;
    }

    public Number getSys_level() {
        return sys_level;
    }

    public void setSys_level(Number sys_level) {
        this.sys_level = sys_level;
    }

    public String getAssociated_unionid() {
        return associated_unionid;
    }

    public void setAssociated_unionid(String associated_unionid) {
        this.associated_unionid = associated_unionid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
