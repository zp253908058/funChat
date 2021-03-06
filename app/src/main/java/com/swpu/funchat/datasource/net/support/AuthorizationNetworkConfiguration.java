package com.swpu.funchat.datasource.net.support;

import okhttp3.HttpUrl;
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

    private static final String BASE_URL = "http://localhost:8080/funchat/api/";

    @Override
    public OkHttpClient configure(OkHttpClient.Builder builder) {
        super.configure(builder);
        return builder.build();
    }

    @Override
    protected HttpUrl.Builder buildUrl(HttpUrl.Builder builder) {
        super.buildUrl(builder);
        builder.addEncodedPathSegment("api");
        builder.addEncodedPathSegment("");
        return builder;
    }
}
