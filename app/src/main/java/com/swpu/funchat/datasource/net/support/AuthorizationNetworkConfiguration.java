package com.swpu.funchat.datasource.net.support;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AuthorizationNetworkConfiguration
 * @since 2019-05-30
 */
public class AuthorizationNetworkConfiguration extends SimpleNetworkConfiguration {

    private static final String TAG = AuthorizationNetworkConfiguration.class.getSimpleName();
    private static final String BASE_URL = "http://10.29.4.211:8080/funchat/api/";
    private static final int TIMEOUT = 30;

    @Override
    public OkHttpClient configure(OkHttpClient.Builder builder) {
        builder.retryOnConnectionFailure(true);
//        builder.authenticator(new TokenAuthenticator());
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
//        mHeaderInterceptor = new HeaderInterceptor();
//        builder.addInterceptor(mHeaderInterceptor);
//        builder.addInterceptor(new LoggingInterceptor());
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        builder.addInterceptor(httpLoggingInterceptor);
        return builder.build();
    }

    @Override
    public String baseUrl() {
        return BASE_URL;
    }
}
