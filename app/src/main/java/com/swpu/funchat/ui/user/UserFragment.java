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
 * 用户信息界面
 *
 * @author zp
 * @version 1.0
 * @see UserFragment
 * @since 2019-06-04
 */
public class UserFragment extends NavigationFragment implements View.OnClickListener {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_user;
    }

    private UserViewModel mUserViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
    }

    @Override
    protected void initView() {
        super.initView();
        View avatarLayout = findViewById(R.id.user_avatar_layout);
        avatarLayout.setOnClickListener(this);
        View nicknameLayout = findViewById(R.id.user_nickname_layout);
        nicknameLayout.setOnClickListener(this);
        View dimensionalBarcodeLayout = findViewById(R.id.user_dimensional_barcode_layout);
        dimensionalBarcodeLayout.setOnClickListener(this);
        View moreLayout = findViewById(R.id.user_more_layout);
        moreLayout.setOnClickListener(this);

        mUserViewModel.getUserObservable().observe(this, userEntity -> {
            TextView name = findViewById(R.id.user_nickname);
            TextView account = findViewById(R.id.user_funchat_account);
            RadiusImageView avatar = findViewById(R.id.user_avatar);

            name.setText(userEntity.getNickname());
            account.setText(String.valueOf(userEntity.getUserId()));
            Glide.with(UserFragment.this).load(userEntity.getAvatar()).into(avatar);
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.user_avatar_layout:
                chooseAvatar();
                break;
            case R.id.user_nickname_layout:
                changeNickname();
                break;
            case R.id.user_dimensional_barcode_layout:
                showDimensionalBarcode();
                break;
            case R.id.user_more_layout:
                showMore();
                break;
        }
    }

    private void chooseAvatar() {
        navigate(R.id.action_userFragment_to_avatarChooseFragment);
    }

    private void changeNickname() {
        navigate(R.id.action_userFragment_to_updateNicknameFragment);
    }

    private void showDimensionalBarcode() {
        navigate(R.id.action_userFragment_to_dimensionalBarcodeFragment);
    }

    private void showMore() {
        navigate(R.id.action_userFragment_to_moreFragment);
    }


}
