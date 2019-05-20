package com.swpu.funchat.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swpu.funchat.R;
import com.swpu.funchat.base.ToolbarActivity;

/**
 * Class description:
 * 首页
 *
 * @author zp
 * @version 1.0
 * @see HomeActivity
 * @since 2019-05-09
 */
public class HomeActivity extends ToolbarActivity implements NavController.OnDestinationChangedListener {

    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HomeActivity.class);
        context.startActivity(intent);
    }

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showNavigationIcon(false);

        if (savedInstanceState == null) {
            setupBottomNavigationBar();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupBottomNavigationBar();
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        mNavController = Navigation.findNavController(this, R.id.home_nav_host_fragment);
        mNavController.addOnDestinationChangedListener(this);
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController);
        bottomNavigationView.setSelectedItemId(R.id.message_fragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> NavigationUI.onNavDestinationSelected(item, mNavController));
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        CharSequence label = destination.getLabel();
        if (!TextUtils.isEmpty(label)) {
            setTitle(label.toString());
        }
    }
}
