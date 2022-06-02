package io.github.zhiweicoding.entity;

import java.util.HashMap;

/**
 * @Created by zhiwei on 2022/3/11.
 */
public class BaseMsg extends HashMap<String, Object> {

    static BaseMsg thisDict = new BaseMsg();
    public static final String CODE = "code";
    public static final String MSG = "msg";

    public static BaseMsg get() {
        thisDict.clear();
        return thisDict;
    }

    public BaseMsg setCode(int code) {
        this.put(CODE, code);
        return this;
    }

    public BaseMsg setMsg(String msg) {
        this.put(MSG, msg);
        return this;
    }
}
