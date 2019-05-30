package com.swpu.funchat.datasource.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see DefaultNetworkConfiguration
 * @since 2019-05-30
 */
public class DefaultNetworkConfiguration implements NetworkConfigurationAdapter {

    private static final String TAG = DefaultNetworkConfiguration.class.getSimpleName();
    private static final String BASE_URL = "http://10.29.5.175:8080/im/";

    @Override
    public void configure(Retrofit.Builder builder) {
        builder.baseUrl(BASE_URL);
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync());
        builder.addConverterFactory(GsonConverterFactory.create());
    }
}
