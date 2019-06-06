package com.swpu.funchat.ui.contact;

import android.content.Context;
import android.content.Intent;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationActivity;
import com.swpu.funchat.base.ToolbarActivity;
import com.swpu.funchat.ui.setting.SettingActivity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ContactAddActivity
 * @since 2019-06-06
 */
public class ContactAddActivity extends NavigationActivity {

    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ContactAddActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getGraph() {
        return R.navigation.contact_add_navigation;
    }
}
