package com.swpu.funchat.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.swpu.funchat.BuildConfig;

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

        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this);
    }
}
