package com.swpu.funchat.datasource.net.support;

import java.util.List;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
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
    @Override
    public OkHttpClient configure(OkHttpClient.Builder builder) {
        return null;
    }

    @Override
    public Call.Factory callFactory() {
        return null;
    }

    @Override
    public String baseUrl() {
        return null;
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
