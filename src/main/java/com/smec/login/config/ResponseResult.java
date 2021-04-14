package com.smec.login.config;


public class ResponseResult<T> {

    //返回码
    private String code;
    private boolean success;
    private String message;
    //返回具体内容
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(String code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(String message,T data){
        this.code="200";
        this.success=true;
        this.message=message;
        this.data=data;
    }

    public ResponseResult() {
    }
}
