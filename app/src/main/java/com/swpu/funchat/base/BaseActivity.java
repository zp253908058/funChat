package com.swpu.funchat.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.swpu.funchat.datasource.cache.AppServiceRegistry;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see BaseActivity
 * @since 2019-05-07
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Object getAppService(@NonNull String name) {
        return AppServiceRegistry.getAppService(name);
    }

    public String getAppServiceName(Class<?> serviceClass) {
        return AppServiceRegistry.getAppServiceName(serviceClass);
    }

    @SuppressWarnings("unchecked")
    public final @Nullable <T> T getAppService(@NonNull Class<T> serviceClass) {
        String serviceName = getAppServiceName(serviceClass);
        return serviceName != null ? (T)getAppService(serviceName) : null;
    }
}
