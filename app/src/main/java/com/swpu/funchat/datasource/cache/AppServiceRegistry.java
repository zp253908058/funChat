package com.swpu.funchat.datasource.cache;

import android.util.Log;

import java.util.HashMap;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AppServiceRegistry
 * @since 2019/6/23
 */
public class AppServiceRegistry {
    private static final String TAG = "AppServiceRegistry";
    public static final String USER_SERVICE = "user_service";


    private static final HashMap<Class<?>, String> APP_SERVICE_NAMES = new HashMap<>();
    private static final HashMap<String, ServiceFetcher<?>> APP_SERVICE_FETCHERS = new HashMap<>();
    private static int sServiceCacheSize;

    // Not instantiable.
    private AppServiceRegistry() {
    }

    static {
        registerService(USER_SERVICE, UserService.class, new StaticServiceFetcher<UserService>() {
            @Override
            public UserService createService() throws ServiceNotFoundException {
                return new UserService();
            }
        });
    }

    static abstract interface ServiceFetcher<T> {
        T getService();
    }

    public static Object getAppService(String name) {
        ServiceFetcher<?> fetcher = APP_SERVICE_FETCHERS.get(name);
        return fetcher != null ? fetcher.getService() : null;
    }

    public static String getAppServiceName(Class<?> serviceClass) {
        return APP_SERVICE_NAMES.get(serviceClass);
    }

    private static <T> void registerService(String serviceName, Class<T> serviceClass, ServiceFetcher<T> serviceFetcher) {
        APP_SERVICE_NAMES.put(serviceClass, serviceName);
        APP_SERVICE_FETCHERS.put(serviceName, serviceFetcher);
    }

//    static abstract class CachedServiceFetcher<T> implements ServiceFetcher<T> {
//        private final int mCacheIndex;
//
//        CachedServiceFetcher() {
//            mCacheIndex = sServiceCacheSize++;
//        }
//
//        @Override
//        @SuppressWarnings("unchecked")
//        public final T getService() {
//            final Object[] cache = ctx.mServiceCache;
//            final int[] gates = ctx.mServiceInitializationStateArray;
//
//            for (;;) {
//                boolean doInitialize = false;
//                synchronized (cache) {
//                    // Return it if we already have a cached instance.
//                    T service = (T) cache[mCacheIndex];
//                    if (service != null || gates[mCacheIndex] == ContextImpl.STATE_NOT_FOUND) {
//                        return service;
//                    }
//
//                    // If we get here, there's no cached instance.
//
//                    // Grr... if gate is STATE_READY, then this means we initialized the service
//                    // once but someone cleared it.
//                    // We start over from STATE_UNINITIALIZED.
//                    if (gates[mCacheIndex] == ContextImpl.STATE_READY) {
//                        gates[mCacheIndex] = ContextImpl.STATE_UNINITIALIZED;
//                    }
//
//                    // It's possible for multiple threads to get here at the same time, so
//                    // use the "gate" to make sure only the first thread will call createService().
//
//                    // At this point, the gate must be either UNINITIALIZED or INITIALIZING.
//                    if (gates[mCacheIndex] == ContextImpl.STATE_UNINITIALIZED) {
//                        doInitialize = true;
//                        gates[mCacheIndex] = ContextImpl.STATE_INITIALIZING;
//                    }
//                }
//
//                if (doInitialize) {
//                    // Only the first thread gets here.
//
//                    T service = null;
//                    @ServiceInitializationState int newState = ContextImpl.STATE_NOT_FOUND;
//                    try {
//                        // This thread is the first one to get here. Instantiate the service
//                        // *without* the cache lock held.
//                        service = createService(ctx);
//                        newState = ContextImpl.STATE_READY;
//
//                    } catch (ServiceNotFoundException e) {
//                        onServiceNotFound(e);
//
//                    } finally {
//                        synchronized (cache) {
//                            cache[mCacheIndex] = service;
//                            gates[mCacheIndex] = newState;
//                            cache.notifyAll();
//                        }
//                    }
//                    return service;
//                }
//                // The other threads will wait for the first thread to call notifyAll(),
//                // and go back to the top and retry.
//                synchronized (cache) {
//                    while (gates[mCacheIndex] < ContextImpl.STATE_READY) {
//                        try {
//                            cache.wait();
//                        } catch (InterruptedException e) {
//                            Log.w(TAG, "getService() interrupted");
//                            Thread.currentThread().interrupt();
//                            return null;
//                        }
//                    }
//                }
//            }
//        }
//
//        public abstract T createService() throws ServiceNotFoundException;
//    }

    /**
     * Override this class when the system service does not need a ContextImpl
     * and should be cached and retained process-wide.
     */
    static abstract class StaticServiceFetcher<T> implements ServiceFetcher<T> {
        private T mCachedInstance;

        @Override
        public final T getService() {
            synchronized (StaticServiceFetcher.this) {
                if (mCachedInstance == null) {
                    try {
                        mCachedInstance = createService();
                    } catch (ServiceNotFoundException e) {
                        onServiceNotFound(e);
                    }
                }
                return mCachedInstance;
            }
        }

        public abstract T createService() throws ServiceNotFoundException;
    }

    /**
     * Like StaticServiceFetcher, creates only one instance of the service per application, but when
     * creating the service for the first time, passes it the application context of the creating
     * application.
     * <p>
     * TODO: Delete this once its only user (ConnectivityManager) is known to work well in the
     * case where multiple application components each have their own ConnectivityManager object.
     */
    static abstract class StaticApplicationContextServiceFetcher<T> implements ServiceFetcher<T> {
        private T mCachedInstance;

        @Override
        public final T getService() {
            synchronized (StaticApplicationContextServiceFetcher.this) {
                if (mCachedInstance == null) {
                    try {
                        mCachedInstance = createService();
                    } catch (ServiceNotFoundException e) {
                        onServiceNotFound(e);
                    }
                }
                return mCachedInstance;
            }
        }

        public abstract T createService() throws ServiceNotFoundException;
    }

    public static void onServiceNotFound(ServiceNotFoundException e) {
        if (android.os.Process.myUid() < android.os.Process.FIRST_APPLICATION_UID) {
            Log.wtf(TAG, e.getMessage(), e);
        } else {
            Log.w(TAG, e.getMessage());
        }
    }
}
