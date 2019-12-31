package com.bawei.libolun20191231.utils;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author 李博伦
 * @createTime 2019/12/31 9:02
 * @description 封装网络工具类
 */
public class OkhttpUtil {

    private static OkhttpUtil okhttpUtil;
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    private OkhttpUtil() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static OkhttpUtil getInstance() {
        if (okhttpUtil != null) {
            synchronized (OkhttpUtil.class) {
                if (okhttpUtil != null) {
                    okhttpUtil = new OkhttpUtil();
                }
            }
        }
        return okhttpUtil;
    }

    //get请求
    public void doget(String url, OkhttpCallback okhttpCallback) {

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (okhttpCallback != null) {
                    okhttpCallback.onfailuer(e);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                if (okhttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.onsuccess(result);
                        }
                    });
                }
            }
        });
    }

    public interface OkhttpCallback {
        void onsuccess(String result);

        void onfailuer(Throwable throwable);
    }
}
