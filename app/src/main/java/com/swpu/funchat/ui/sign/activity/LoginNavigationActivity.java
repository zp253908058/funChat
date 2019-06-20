package com.swpu.funchat.ui.sign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.swpu.funchat.R;
import com.swpu.funchat.base.ToolbarActivity;
import com.swpu.funchat.ui.NavigationActivity;
import com.swpu.funchat.vm.LoginViewModel;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see LoginNavigationActivity
 * @since 2019-06-16
 */
public class LoginNavigationActivity extends ToolbarActivity implements NavController.OnDestinationChangedListener {
    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginNavigationActivity.class);
        context.startActivity(intent);
    }

    protected NavController mNavController;
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_navigation);
        init();
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mLoginViewModel.getLoginSuccessObservable().observe(this, integer -> {
            if (integer == null) {
                return;
            }
            if (integer == 1) {
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                NavigationActivity.go(LoginNavigationActivity.this);
                finish();
            }
        });
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
