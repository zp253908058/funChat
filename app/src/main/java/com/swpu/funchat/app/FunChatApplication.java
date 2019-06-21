package com.swpu.funchat.app;

import android.app.Application;

import com.swpu.funchat.util.Logger;
import com.swpu.funchat.util.Toaster;

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
        Toaster.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
