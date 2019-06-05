package com.swpu.funchat.base;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;

public class NavigationFragment extends BaseFragment {

    private NavController mNavController;

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mNavController = Navigation.findNavController(view);
    }

    protected NavController getNavController() {
        return mNavController;
    }

    protected void navigate(@IdRes int resId) {
        mNavController.navigate(resId);
    }

    protected void navigate(@IdRes int resId, @Nullable Bundle args) {
        mNavController.navigate(resId, args);
    }

    protected void navigate(@IdRes int resId, @Nullable Bundle args, @Nullable NavOptions navOptions) {
        mNavController.navigate(resId, args, navOptions);
    }

    protected void navigate(@IdRes int resId, @Nullable Bundle args, @Nullable NavOptions navOptions, @Nullable Navigator.Extras navigatorExtras) {
        mNavController.navigate(resId, args, navOptions, navigatorExtras);
    }

    protected void navigate(@NonNull Uri deepLink) {
        mNavController.navigate(deepLink);
    }

    protected void navigate(@NonNull Uri deepLink, @Nullable NavOptions navOptions) {
        mNavController.navigate(deepLink, navOptions);
    }

    protected void navigate(@NonNull Uri deepLink, @Nullable NavOptions navOptions, @Nullable Navigator.Extras navigatorExtras) {
        mNavController.navigate(deepLink, navOptions, navigatorExtras);
    }

    protected void navigate(@NonNull NavDirections directions) {
        mNavController.navigate(directions);
    }

    protected void navigate(@NonNull NavDirections directions, @Nullable NavOptions navOptions) {
        mNavController.navigate(directions, navOptions);
    }

    protected void navigate(@NonNull NavDirections directions, @NonNull Navigator.Extras navigatorExtras) {
        mNavController.navigate(directions, navigatorExtras);
    }
}
