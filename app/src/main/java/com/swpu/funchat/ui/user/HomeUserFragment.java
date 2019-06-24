package com.swpu.funchat.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.vm.UserViewModel;
import com.swpu.funchat.widget.RadiusImageView;

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

    private UserViewModel mUserViewModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_user;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
    }

    @Override
    protected void initView() {
        View head = findViewById(R.id.home_user_head);
        head.setOnClickListener(this);

        View setting = findViewById(R.id.home_user_setting);
        setting.setOnClickListener(this);

        mUserViewModel.getUserObservable().observe(this, userEntity -> {
            TextView name = findViewById(R.id.home_user_name);
            TextView account = findViewById(R.id.home_user_account);
            RadiusImageView avatar = findViewById(R.id.home_user_avatar);

            name.setText(userEntity.getNickname());
            account.setText(String.valueOf(userEntity.getUserId()));
            Glide.with(HomeUserFragment.this).load(userEntity.getAvatar()).into(avatar);
        });
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
