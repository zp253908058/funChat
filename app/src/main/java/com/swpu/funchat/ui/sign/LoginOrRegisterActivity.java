package com.swpu.funchat.ui.sign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationActivity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see LoginOrRegisterActivity
 * @since 2019-05-17
 */
public class LoginOrRegisterActivity extends NavigationActivity {

    private static final String TAG = LoginOrRegisterActivity.class.getSimpleName();

    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginOrRegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setGraph(R.navigation.login_navigation);
        showNavigationIcon(false);
    }

    @Override
    public int getGraph() {
        return R.navigation.login_navigation;
    }
}
