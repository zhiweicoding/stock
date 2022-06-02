package io.github.zhiweicoding.entity;

import java.io.Serializable;

/**
 *
 * @Created by zhiwei on 2022/3/11.
 */
public class BaseResponse<T> implements Serializable {
    private String msg;
    private int code;//1000 success 10001 fail 1002 no auth
    private T body;

    public BaseResponse() {
    }

    public BaseResponse(String msg, int code, T body) {
        this.msg = msg;
        this.code = code;
        this.body = body;
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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", body=" + body +
                '}';
    }
}
