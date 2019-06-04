package com.swpu.funchat.ui.user;

import android.content.Context;
import android.content.Intent;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationActivity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserActivity
 * @since 2019-06-04
 */
public class UserActivity extends NavigationActivity {

    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UserActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getGraph() {
        return R.navigation.user_navigation;
    }
}
