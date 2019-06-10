package com.swpu.funchat.ui.user;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;

/**
 * Class description:
 * 首页我的页面
 *
 * @author zp
 * @version 1.0
 * @see HomeUserFragment
 * @since 2019-05-09
 */
public class HomeUserFragment extends NavigationFragment implements View.OnClickListener {

    public static HomeUserFragment newInstance() {
        return new HomeUserFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_user;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View head = findViewById(R.id.home_user_head);
        head.setOnClickListener(this);

        View setting = findViewById(R.id.home_user_setting);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.home_user_head:
                navigate(R.id.action_home_fragment_to_userFragment);
                break;
            case R.id.home_user_setting:
                navigate(R.id.action_home_fragment_to_settingFragment);
                break;
        }
    }
}
