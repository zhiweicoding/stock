package io.github.zhiweicoding.support;

import org.apache.http.concurrent.FutureCallback;

import java.util.concurrent.CompletableFuture;

/**
 * @Created by zhiwei on 2022/3/29.
 */
public interface Support<T> {

    /**
     * 初始化
     */
    default void init(T t) {
    }

    default void start() {
    }

    default void start(String api) {
    }

    default boolean stop() {
        return false;
    }

    default boolean clear() {
        return false;
    }

    default boolean clear(String msg) {
        return false;
    }
}
