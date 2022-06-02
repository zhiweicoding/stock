package io.github.zhiweicoding.support;

import io.github.zhiweicoding.entity.BaseResponse;

/**
 * @Created by zhiwei on 2022/3/27.
 */
public class ResponseFactory<T> {

    public BaseResponse<T> success(T t) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(StatsEnum.SUCCESS.code);
        response.setBody(t);
        response.setMsg(StatsEnum.SUCCESS.name);
        return response;
    }

    public BaseResponse<T> fail(T t) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(StatsEnum.FAIL.code);
        response.setBody(t);
        response.setMsg(StatsEnum.FAIL.name);
        return response;
    }

    public BaseResponse<T> get(StatsEnum statsEnum, T t) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(statsEnum.code);
        response.setBody(t);
        response.setMsg(statsEnum.name);
        return response;
    }

    public static enum StatsEnum {
        SUCCESS(1000, "成功"),
        FAIL(1001, "失败"),
        NO_TOKEN(1002, "没有登陆状态"),
        I_DO_NOT_KNOW(1004, "未知错误"),
        ERROR_URL(1003, "错误的地址");

        private final int code;
        private final String name;

        StatsEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getName(int code) {
            for (StatsEnum c : StatsEnum.values()) {
                if (c.getCode() == code) {
                    return c.name;
                }
            }
            return "未知错误";
        }
    }
}
