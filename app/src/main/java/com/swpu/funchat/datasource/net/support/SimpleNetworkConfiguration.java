package com.swpu.funchat.datasource.net.support;

import androidx.annotation.CallSuper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see SimpleNetworkConfiguration
 * @since 2019/6/17
 */
class SimpleNetworkConfiguration implements NetworkConfigurationAdapter {

    private static final String WINDOWS_HOST = "192.168.1.28";
    private static final String MAC_HOST = "10.29.1.142";
    private static final String SERVER_HOST = "47.106.84.158";
    private static final String HOST = SERVER_HOST;
    private static final int TIMEOUT = 30;

    @Override
    @CallSuper
    public OkHttpClient configure(OkHttpClient.Builder builder) {
        builder.retryOnConnectionFailure(true);
//        builder.authenticator(new TokenAuthenticator());
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        HeaderInterceptor headerInterceptor = new HeaderInterceptor();
        builder.addInterceptor(headerInterceptor);
//        builder.addInterceptor(new LoggingInterceptor());
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
        return null;
    }

    @Override
    public Call.Factory callFactory() {
        return null;
    }

    @Override
    public HttpUrl baseUrl() {
        return buildUrl(new HttpUrl.Builder()).build();
    }

    @CallSuper
    protected HttpUrl.Builder buildUrl(HttpUrl.Builder builder) {
        builder.scheme("http");
        builder.port(80);
        builder.host(HOST);
        builder.addEncodedPathSegment("funchat");
        builder.addPathSegment("");
        return builder;
    }

    @Override
    public Executor callbackExecutor() {
        return null;
    }

    @Override
    public void callAdapterFactories(List<CallAdapter.Factory> factories) {
        factories.add(RxJava2CallAdapterFactory.createAsync());
    }

    @Override
    public void converterFactories(List<Converter.Factory> factories) {
        factories.add(GsonConverterFactory.create());
    }

    @Override
    public boolean validateEagerly() {
        return true;
    }
}
