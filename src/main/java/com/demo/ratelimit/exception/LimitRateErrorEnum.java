package com.demo.ratelimit.exception;

/**
 * Description :
 *
 * @author  syj
 * CreateTime    2018/09/07
 * Description    通用错误信息
 */
public enum LimitRateErrorEnum {

    TOO_MANY_REQUESTS(50001, "personal-resource-rateLimit say: You have made too many requests,please try again later!!!"),
    USER_NOT_DOUND(50002, "personal-resource-rateLimit say: not found user info ,please check request.getUserPrincipal().getName()!!!"),
    CUSTOM_NOT_DOUND(50003, "personal-resource-rateLimit say: not found custom info ,please check request.getAttribute('syj-rateLimit-custom')!!!"),
    BLACK_REQUESTS(50004, "personal-resource-rateLimit say: You are in black list"),
    IP_ERROR(50005, "personal-resource-rateLimit say： ip error");
    private final String msg;

    private final Integer code;

    LimitRateErrorEnum(Integer code, String msg){
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

}
