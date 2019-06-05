package com.swpu.funchat.ui.user.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserFragment
 * @since 2019-06-04
 */
public class UserFragment extends NavigationFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View avatarLayout = view.findViewById(R.id.user_avatar_layout);
        avatarLayout.setOnClickListener(this);
        View nicknameLayout = view.findViewById(R.id.user_nickname_layout);
        nicknameLayout.setOnClickListener(this);
        View dimensionalBarcodeLayout = view.findViewById(R.id.user_dimensional_barcode_layout);
        dimensionalBarcodeLayout.setOnClickListener(this);
        View moreLayout = view.findViewById(R.id.user_more_layout);
        moreLayout.setOnClickListener(this);
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
