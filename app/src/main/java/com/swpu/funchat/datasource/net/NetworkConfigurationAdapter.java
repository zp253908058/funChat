package com.swpu.funchat.datasource.net;

import retrofit2.Retrofit;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see NetworkConfigurationAdapter
 * @since 2019-05-30
 */
public interface NetworkConfigurationAdapter {

    void configure(Retrofit.Builder builder);


}
