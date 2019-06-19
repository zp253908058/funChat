package com.swpu.funchat.datasource.net.support;


import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see HeaderInterceptor
 * @since 2019/6/17
 */
public class HeaderInterceptor implements Interceptor {


    @SuppressWarnings("NullableProblems")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        builder.header("Content-Type", "application/json;charset=UTF-8");

//        AccountPreference entity = mUserManager.get();
//        if (!entity.isEmpty()) {
//            builder.header("Authorization", entity.getTokenType() + " " + entity.getAccessToken());
//        }
//        builder.header("sId", String.valueOf(mWarehouseId));
        Request newRequest = builder.build();

        return chain.proceed(newRequest);
    }
}