package com.swpu.funchat.ui.sign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.swpu.funchat.R;
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
public class LoginOrRegisterActivity extends ToolbarActivity {

    private static final String TAG = LoginOrRegisterActivity.class.getSimpleName();

    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginOrRegisterActivity.class);
        context.startActivity(intent);
    }

    private UserViewModel mUserViewModel;

    private EditText mUsernameText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        mUserViewModel = ViewModelProviders.of(this).get("user", UserViewModel.class);
        mUsernameText = findViewById(R.id.login_username);

        Observer<String> observer = s -> mUsernameText.setText(s);
        mUserViewModel.getUsernameLiveData().observe(this, observer);
    }
}
