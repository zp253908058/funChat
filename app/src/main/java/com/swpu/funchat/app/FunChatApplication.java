package com.swpu.funchat.app;

import android.app.Application;

import com.swpu.funchat.util.Logger;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see FunChatApplication
 * @since 2019-05-07
 */
public class FunChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
