package com.burgess.banana.common.exception;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.exception
 * @file_name RRException.java
 * @description 自定义异常
 * @create 2018-04-18 22:15
 */
public class RRException extends RuntimeException {

    private String msg;
    private int code = 500;

    public RRException(String msg){
        super(msg);
        this.msg = msg;
    }

    public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RRException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
