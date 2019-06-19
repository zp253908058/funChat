package com.swpu.funchat.datasource.net.support;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see Network
 * @since 2019-05-30
 */
public class Network {

    private static final String KEY_GENERAL = "general";
    private static final String KEY_AUTHORIZATION = "authorization";

    private static Network sNetwork;
    private final Object mLock = new Object();

    private RetrofitStore mRetrofitStore;

    private Network() {
        mRetrofitStore = new RetrofitStore();
        mRetrofitStore.put(KEY_GENERAL, RetrofitBuilder.buildRetrofit(new GeneralNetworkConfiguration()));
        mRetrofitStore.put(KEY_AUTHORIZATION, RetrofitBuilder.buildRetrofit(new AuthorizationNetworkConfiguration()));
    }

    public static Network getInstance() {
        if (sNetwork == null) {
            synchronized (Network.class) {
                if (sNetwork == null) {
                    sNetwork = new Network();
                }
            }
        }
        return sNetwork;
    }

    public <T> T getAuthorizationService(Class<T> clz) {
        synchronized (mLock) {
            return mRetrofitStore.getServiceWithKey(KEY_AUTHORIZATION, clz);
        }
    }

    public <T> T getGeneralService(Class<T> clz) {
        synchronized (mLock) {
            return mRetrofitStore.getServiceWithKey(KEY_GENERAL, clz);
        }
    }
}
