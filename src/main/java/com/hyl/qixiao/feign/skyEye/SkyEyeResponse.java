package com.hyl.qixiao.feign.skyEye;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author maoshunhui@qiyi.com
 * @Date 2019/10/16
 **/
public class SkyEyeResponse<T> {

    @JsonProperty("error_code")
    private Integer errorCode;

    private T result;

    private String reason;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
