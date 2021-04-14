package com.smec.login.dto;

/**
 * 钉钉接口返回信息
 * @author 刘鸿亮
 */
public class DingDTO {

    //返回码。
    private int errcode;
    private String access_token;

    //返回码描述。
    private String errmsg;

    //access_token的过期时间，单位秒。
    private Integer expires_in;

    // 请求ID
    private String request_id;

    // 返回的用户信息
    private UserGetByCodeResponse result;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public UserGetByCodeResponse getResult() {
        return result;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public void setResult(UserGetByCodeResponse result) {
        this.result = result;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public DingDTO() {
    }

    public DingDTO(int errcode, String access_token, String errmsg, Integer expires_in, String request_id, UserGetByCodeResponse result) {
        this.errcode = errcode;
        this.access_token = access_token;
        this.errmsg = errmsg;
        this.expires_in = expires_in;
        this.request_id = request_id;
        this.result = result;
    }
}
