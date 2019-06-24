package com.swpu.funchat.ui.sign;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.util.Validator;
import com.swpu.funchat.vm.SignViewModel;

/**
 * Class description:
 * 注册界面
 *
 * @author zp
 * @version 1.0
 * @see RegisterFragment
 * @since 2019-05-21
 */
public class RegisterFragment extends NavigationFragment implements View.OnClickListener {

    private EditText mUsernameText;
    private EditText mPasswordText;
    private EditText mConfirmPasswordText;

    private SignViewModel mSignViewModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_register;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignViewModel = ViewModelProviders.of(this).get(SignViewModel.class);
        mSignViewModel.addOnResponseListener(SignViewModel.KEY_REGISTER_RESPONSE_LISTENER, () -> getNavController().navigateUp());
    }

    @Override
    protected void initView() {
        View register = findViewById(R.id.register_button);
        register.setOnClickListener(this);

        mUsernameText = findViewById(R.id.register_username);
        mPasswordText = findViewById(R.id.register_password);
        mConfirmPasswordText = findViewById(R.id.register_password_confirm);
    }

    @Override
    public void onClick(View v) {
        attemptRegister();
    }

    private void attemptRegister() {
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

        String confirmPassword = mConfirmPasswordText.getText().toString();
        if (!password.equals(confirmPassword)) {
            mConfirmPasswordText.setError(getString(R.string.error_password_inconsistent));
            mConfirmPasswordText.requestFocus();
            return;
        }

        mSignViewModel.register(username, password);
    }
}
