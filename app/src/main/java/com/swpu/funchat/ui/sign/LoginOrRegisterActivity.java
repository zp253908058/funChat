package com.swpu.funchat.ui.sign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationActivity;
import com.swpu.funchat.base.ToolbarActivity;
import com.swpu.funchat.vm.UserViewModel;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see LoginOrRegisterActivity
 * @since 2019-05-17
 */
public class LoginOrRegisterActivity extends NavigationActivity implements NavController.OnDestinationChangedListener {

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

        mNavController.addOnDestinationChangedListener(this);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        CharSequence title = destination.getLabel();
        if (!TextUtils.isEmpty(title)) {
            mToolbar.setTitle(title);
        }
    }
}
