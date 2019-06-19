package com.swpu.funchat.datasource.net.support;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see RetrofitBuilder
 * @since 2019/6/17
 */
public class RetrofitBuilder {

    public static Retrofit buildRetrofit(NetworkConfigurationAdapter adapter){
        if (adapter == null) {
            throw new NullPointerException("NetworkConfigurationAdapter == null");
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        builder.client(adapter.configure(okBuilder));
        if (adapter.callFactory() != null) {
            builder.callFactory(adapter.callFactory());
        }
        if (adapter.callbackExecutor() != null) {
            builder.callbackExecutor(adapter.callbackExecutor());
        }
        builder.baseUrl(adapter.baseUrl());
        adapter.callAdapterFactories(builder.callAdapterFactories());
        adapter.converterFactories(builder.converterFactories());
        builder.validateEagerly(adapter.validateEagerly());
        return builder.build();
    }
}
