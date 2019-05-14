package com.swpu.funchat.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see BaseActivity
 * @since 2019-05-07
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ARouter mRouter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRouter = ARouter.getInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRouter.destroy();
    }

    public void navigation(String path){
        mRouter.build(path).navigation();
    }
}
