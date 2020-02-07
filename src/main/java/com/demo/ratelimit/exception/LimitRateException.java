package com.demo.ratelimit.exception;

/**
 * @description:
 * @author:zhaiyarong
 * @Date:2019/12/31 14:42
 */
public class LimitRateException extends RuntimeException{
    private String msg;

    private Integer code;

    public LimitRateException(LimitRateErrorEnum error) {
        super(error.getMsg());
        this.msg = error.getMsg();
        this.code = error.getCode();
    }

    public LimitRateException(String msg, Integer code){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
