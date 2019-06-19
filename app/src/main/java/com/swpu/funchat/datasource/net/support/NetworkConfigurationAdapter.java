package com.swpu.funchat.datasource.net.support;

import java.util.List;
import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see NetworkConfigurationAdapter
 * @since 2019-05-30
 */
public interface NetworkConfigurationAdapter {

    OkHttpClient configure(OkHttpClient.Builder builder);

    okhttp3.Call.Factory callFactory();

    String baseUrl();

    Executor callbackExecutor();

    void callAdapterFactories(List<CallAdapter.Factory> factories);

    void converterFactories(List<Converter.Factory> factories);

    boolean validateEagerly();
}
