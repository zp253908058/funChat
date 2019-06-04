package com.swpu.funchat.ui.setting;

import android.content.Context;
import android.content.Intent;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationActivity;
import com.swpu.funchat.base.ToolbarActivity;
import com.swpu.funchat.ui.user.UserActivity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see SettingActivity
 * @since 2019-06-04
 */
public class SettingActivity extends NavigationActivity {

    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getGraph() {
        return R.navigation.setting_navigation;
    }
}
