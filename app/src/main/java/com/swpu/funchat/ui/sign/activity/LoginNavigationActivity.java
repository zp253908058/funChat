package com.swpu.funchat.ui.sign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.swpu.funchat.R;
import com.swpu.funchat.base.ToolbarActivity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see LoginNavigationActivity
 * @since 2019-06-16
 */
public class LoginNavigationActivity extends ToolbarActivity implements NavController.OnDestinationChangedListener{
    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginNavigationActivity.class);
        context.startActivity(intent);
    }

    protected NavController mNavController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_navigation);
        init();
    }

    private void init() {
        mNavController = Navigation.findNavController(this, R.id.navigation_host_fragment);
        initNavigation(mNavController);
        mNavController.addOnDestinationChangedListener(this);
    }

    @CallSuper
    protected void initNavigation(NavController controller) {
        NavDestination current = controller.getCurrentDestination();
        if (current == null) {
            return;
        }
        CharSequence label = current.getLabel();
        mToolbar.setTitle(label);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        CharSequence title = destination.getLabel();
        if (!TextUtils.isEmpty(title)) {
            mToolbar.setTitle(title);
        }
        if (destination.getId() == R.id.loginFragment) {
            showNavigationIcon(false);
        } else {
            showNavigationIcon(true);
        }
    }
}
