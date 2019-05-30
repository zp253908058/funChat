package com.swpu.funchat.datasource.net;

import retrofit2.Retrofit;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see Network
 * @since 2019-05-30
 */
public class Network {

    private static Network sNetwork;
    private Retrofit mRetrofit;
    private NetworkConfigurationAdapter mNetworkConfigurationAdapter;

    private Network(NetworkConfigurationAdapter adapter) {
        if (adapter == null) {
            throw new NullPointerException("NetworkConfigurationAdapter == null");
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        adapter.configure(builder);
        mRetrofit = builder.build();
    }

    public static Network getInstance() {
        if (sNetwork == null) {
            synchronized (Network.class) {
                if (sNetwork == null) {
                    sNetwork = new Network(new DefaultNetworkConfiguration());
                }
            }
        }
        return sNetwork;
    }

    public <T> T getService(Class<T> service) {
        return mRetrofit.create(service);
    }
}
