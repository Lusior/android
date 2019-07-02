package com.longdian.service.interceptor;

import android.text.TextUtils;

import com.longdian.bean.GlobalInfo;
import com.longdian.util.LogUtil;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/14.
 * 请求头拦截器
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        // 添加请求头
        builder.addHeader("ajax", "true");
        if (GlobalInfo.oprInfo != null && !TextUtils.isEmpty(GlobalInfo.oprInfo.getToken())) {
            builder.addHeader("cookie", "shiro.session=" + GlobalInfo.oprInfo.getToken());
        }
//        for (Map.Entry<String, String> e : AccessHttpService.getHeaders().entrySet()) {
//            builder.addHeader(e.getKey(), e.getValue());
//        }
        Request newReq = builder.build();

        // 添加Token
        newReq = injectTokenIntoUrl(newReq);

        // 添加请求参数
//        newReq = injectSignIntoUrl(newReq);

        return chain.proceed(newReq);
    }

    // func to inject params into url
    private Request injectTokenIntoUrl(Request request) {
        HttpUrl httpUrl = request.url();
//        if (httpUrl.url().getPath().contains("public")) {
//            return request;
//        }
        HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();
//        httpUrlBuilder.addQueryParameter("JSESSIONID", "d4813da3-49b4-42d6-916a-947a148e69c2");
        return request.newBuilder().url(httpUrlBuilder.build()).build();
    }

    private Request injectSignIntoUrl(Request request) {
        if ("POST".equalsIgnoreCase(request.method())) {
            TreeMap<String, String> treeMap = new TreeMap<>();
            HttpUrl httpUrl = request.url();
            if (httpUrl.querySize() <= 0) {
                return request;
            }
            for (int i = 0; i < httpUrl.querySize(); i++) {
                treeMap.put(httpUrl.queryParameterName(i), httpUrl.queryParameterValue(i));
            }
            StringBuilder sb = new StringBuilder();
            for (Map.Entry e : treeMap.entrySet()) {
                sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("|12345");
            LogUtil.print("| SignParams:{" + sb.toString() + "}");

            HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();
//            httpUrlBuilder.addQueryParameter("sign", toMD5(sb.toString()));
            return request.newBuilder().url(httpUrlBuilder.build()).build();
        }
        return request;
    }

}
