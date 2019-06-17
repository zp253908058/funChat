package com.swpu.funchat.ui.sign;

import android.view.View;
import android.widget.EditText;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;
import com.swpu.funchat.model.UserEntity;
import com.swpu.funchat.util.Validator;

/**
 * Class description:
 * 注册界面
 *
 * @author zp
 * @version 1.0
 * @see RegisterFragment
 * @since 2019-05-21
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener {

    private EditText mUsernameText;
    private EditText mPasswordText;
    private EditText mConfirmPasswordText;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_register;
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


    }

    /**
     * 注册账号，并增加一个用户
     *
     * @param username 账号
     * @param password 密码
     */
    private void register(String username, String password) {

    }
}
