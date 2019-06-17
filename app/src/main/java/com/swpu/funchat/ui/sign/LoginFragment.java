package com.swpu.funchat.ui.sign;

import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.datasource.storage.preference.SharedPreferenceManager;
import com.swpu.funchat.datasource.storage.preference.dto.AccountPreference;
import com.swpu.funchat.model.UserEntity;
import com.swpu.funchat.util.Validator;
import com.swpu.funchat.vm.LoginViewModel;

import java.util.logging.Logger;

/**
 * Class description:
 * 登录界面
 *
 * @author zp
 * @version 1.0
 * @see LoginFragment
 * @since 2019-05-21
 */
public class LoginFragment extends NavigationFragment implements View.OnClickListener {

    private EditText mUsernameText;
    private EditText mPasswordText;

    private CheckBox mCheckBox;
    private SharedPreferenceManager mManager = SharedPreferenceManager.getInstance();
    private AccountPreference mEntity = new AccountPreference();

    private LoginViewModel mLoginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginViewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginViewModel.getLoginObservable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    mLoginViewModel.onLoginSuccess();
                }
            }
        });
    }

    @Override
    protected void initView() {
        View view = findViewById(R.id.login_register);
        view.setOnClickListener(this);

        View login = findViewById(R.id.login_button);
        login.setOnClickListener(this);

        mUsernameText = findViewById(R.id.login_username);
        mPasswordText = findViewById(R.id.login_password);

        mCheckBox = findViewById(R.id.login_retain_password);

        boolean success = mManager.load(requireContext(), mEntity);
        if (success) {
            mUsernameText.setText(mEntity.getUsername());
            mPasswordText.setText(mEntity.getPassword());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_button:
                attemptLogin();
                break;
            case R.id.login_register:
                navigate(R.id.action_loginFragment_to_registerFragment);
                break;
        }
    }

    private void attemptLogin() {
        String username = mUsernameText.getText().toString();
        if (Validator.isEmpty(username)) {
            mUsernameText.setError(getString(R.string.error_username_empty));
            mUsernameText.requestFocus();
            return;
        }

        String password = mPasswordText.getText().toString();
        if (Validator.isEmpty(password)) {
            mPasswordText.setError(getString(R.string.error_password_empty));
            mPasswordText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            mPasswordText.setError(getString(R.string.error_password_too_short));
            mPasswordText.requestFocus();
            return;
        }


        mLoginViewModel.login(username, password);
    }

    private void loginSuccess() {
        boolean isRetain = mCheckBox.isChecked();
        if (!isRetain) {
            mEntity.setUsername("");
            mEntity.setPassword("");
        }

        boolean success = mManager.save(requireContext(), mEntity);
        if (!success) {
        }
    }
}
