package com.swpu.funchat.ui.sign;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;

/**
 * Class description:
 * 登录界面
 *
 * @author zp
 * @version 1.0
 * @see LoginFragment
 * @since 2019-05-21
 */
public class LoginFragment extends BaseFragment {

    private NavController mNavController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_login;
    }
}
