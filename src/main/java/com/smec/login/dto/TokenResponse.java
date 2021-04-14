package com.smec.login.dto;

/**
 * token信息
 * @author 刘鸿亮
 * @data 2021/03/31
 */
public class TokenResponse {

    /**
     *  access_token
     */
    private String accessToken;

    /**
     *  token type default value is bearer
     */
    private String tokenType;

    /**
     *  刷新token用的令牌
     */
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TokenResponse(String accessToken, String tokenType, String refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
    }
}
