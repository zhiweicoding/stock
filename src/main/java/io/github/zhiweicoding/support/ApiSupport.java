package io.github.zhiweicoding.support;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.github.zhiweicoding.constants.KeyConstants;
import io.github.zhiweicoding.models.InfoBean;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.concurrent.FutureCallback;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author by diaozhiwei on 2022/06/02.
 * @email diaozhiwei2k@163.com
 */
@Slf4j
@Component
public class ApiSupport implements Support<String> {

    private OkHttpClient client;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void init(String s) {
        client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(30, 5, TimeUnit.MINUTES))
                .build();
    }

    @Override
    public void start(String api) {
        String urlReal = String.format(api, System.currentTimeMillis());
        Request request = new Request.Builder()
                .get()
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Language", "zh-CN,zh-HK;q=0.9,zh;q=0.8,en-ZA;q=0.7,en;q=0.6")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .addHeader("Pragma", "no-cache")
                .addHeader("Referer", "https://www.cls.cn/telegraph")
                .addHeader("Sec-Fetch-Dest", "empty")
                .addHeader("Sec-Fetch-Mode", "cors")
                .addHeader("Sec-Fetch-Site", "same-origin")
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36")
                .addHeader("Sec-Ch-Ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\",\"Google Chrome\";v=\"101\"")
                .addHeader("Sec-Ch-Ua-Mobile", "?0")
                .addHeader("Sec-Ch-Ua-Platform", "\"macOS\"")
                .url(urlReal)
                .build();

        Call call = client.newCall(request);

        //异步调用,并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("调用http client报错了，错误信息:{}", e.getMessage(), e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                String respBody = response.body().string();
                JSONObject lObj = JSON.parseObject(respBody).getJSONObject("l");
                BulkRequest bulkRequest = new BulkRequest();
                for (String key : lObj.keySet()) {
                    if (lObj.getJSONObject(key).containsKey("title") && !KeyConstants.stockInfoArray.contains(key)) {
                        KeyConstants.stockInfoArray.add(key);
                        String value = lObj.getString(key);
                        log.info(value);

                        IndexRequest indexRequest = new IndexRequest("stock_info")
                                .id(key)
                                .source(value, XContentType.JSON);
                        bulkRequest.add(indexRequest);
                    }
                }

                if (bulkRequest.numberOfActions()>0) {
                    restHighLevelClient.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<>() {
                        @Override
                        public void onResponse(BulkResponse response) {
                            if (response.hasFailures()) {
                                log.warn(response.buildFailureMessage());
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    });
                }
                call.cancel();
            }
        });
    }

    @Override
    public boolean stop() {
        try {
            Dispatcher dispatcher = client.dispatcher();
            dispatcher.cancelAll();
            return true;
        } catch (Exception e) {
            log.error("停掉所有的ok http client 时报错了，错误信息:{}", e.getMessage(), e);
            return false;
        }

    }

    @Override
    public boolean clear(String tag) {
        try {
            for (Call call : client.dispatcher().queuedCalls()) {
                if (Objects.requireNonNull(call.request().tag()).toString().contains(tag)) {
                    call.cancel();
                }
            }
            for (Call call : client.dispatcher().runningCalls()) {
                if (Objects.requireNonNull(call.request().tag()).toString().contains(tag)) {
                    call.cancel();
                }
            }
            return true;
        } catch (Exception e) {
            log.error("停掉所有的ok http client 时报错了，错误信息:{}", e.getMessage(), e);
            return false;
        }
    }
}
