package com.longdian.service.interceptor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.longdian.util.LogUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/14.
 * 日志拦截器
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        LogUtil.print("----------Start----------------");
        Request request = chain.request();
        if ("POST".equalsIgnoreCase(request.method())) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < request.url().querySize(); i++) {
                sb.append(request.url().queryParameterName(i)).append("=").append(request.url().queryParameterValue(i)).append(",");
            }
            if (sb.length() > 1) {
                sb.delete(sb.length() - 1, sb.length());
            }
            LogUtil.print("| RequestParams:{" + sb.toString() + "}");
        }

        long t1 = System.currentTimeMillis();
//        LogUtil.print(String.format("| Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        LogUtil.print(String.format("| Sending request %s", request.url()));
        Response response = chain.proceed(request);
        //获取Response的body的字符串 并打印
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        if (contentLength != 0) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            String responseStr = buffer.clone().readUtf8();
            if (response.code() == 500 && checkIsJson(responseStr)) {
                changePorperty(response, "code");
            }
            LogUtil.print("| Response: " + responseStr);
        }

        long t2 = System.currentTimeMillis();
        LogUtil.print("----------End:" + (t2 - t1) + "毫秒----------");
//        Log.i(TAG, String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//        Log.i(TAG, String.format("Received response message %s", response.message()));
        return response;
    }

    private boolean checkIsJson(String string) {
        Gson gson = new Gson();
        try {
            Map<String, Object> map = gson.fromJson(string, Map.class);
            return map.get("code") != null;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    private void changePorperty(Object object, String fieldName) {
        try {
            Class<? extends Object> c = object.getClass();
            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, 200);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
