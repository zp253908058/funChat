package com.swpu.funchat.ui.sign;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseActivity;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.datasource.cache.UserService;
import com.swpu.funchat.datasource.storage.preference.SharedPreferenceManager;
import com.swpu.funchat.datasource.storage.preference.dto.AccountPreference;
import com.swpu.funchat.util.Logger;
import com.swpu.funchat.util.Validator;
import com.swpu.funchat.vm.SignViewModel;

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

    private SignViewModel mSignViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignViewModel = ViewModelProviders.of(requireActivity()).get(SignViewModel.class);
        mSignViewModel.addOnResponseListener(SignViewModel.KEY_LOGIN_RESPONSE_LISTENER, this::loginSuccess);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSignViewModel.getLoginSuccessObservable().observe(this, s -> {
            if (s == null) {
                return;
            }
            if (s == 200) {
                loginSuccess();
                mSignViewModel.getLoginSuccessObservable().postValue(1);
            }
        });
        mSignViewModel.getUserObservable().observe(this, userEntity -> {
            BaseActivity activity = (BaseActivity) requireActivity();
            UserService service = activity.getAppService(UserService.class);
            if (service == null) {
                return;
            }
            service.setUserEntity(userEntity);
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

        Logger.d(success);
        Logger.d(mEntity);

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

        mEntity.setUsername(username);
        mEntity.setPassword(password);

        mSignViewModel.login(username, password);
    }

    private void loginSuccess() {
        boolean isRetain = mCheckBox.isChecked();
        if (!isRetain) {
            mEntity.setUsername("");
            mEntity.setPassword("");
        }

        mManager.save(requireContext(), mEntity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSignViewModel.removeOnResponseListener(SignViewModel.KEY_LOGIN_RESPONSE_LISTENER);
    }
}
