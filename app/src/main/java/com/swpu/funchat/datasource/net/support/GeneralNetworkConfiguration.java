package com.swpu.funchat.datasource.net.support;

import okhttp3.OkHttpClient;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see GeneralNetworkConfiguration
 * @since 2019/6/17
 */
public class GeneralNetworkConfiguration extends SimpleNetworkConfiguration {
    private static final String TAG = AuthorizationNetworkConfiguration.class.getSimpleName();
    private static final String BASE_URL = "http://localhost:8080/funchat/";
    private static final int TIMEOUT = 30;

    @Override
    public OkHttpClient configure(OkHttpClient.Builder builder) {
        super.configure(builder);
        return builder.build();
    }

    @Override
    public String baseUrl() {
        return BASE_URL;
    }

}
