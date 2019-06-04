package com.swpu.funchat.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;
import com.swpu.funchat.ui.setting.SettingActivity;
import com.swpu.funchat.ui.user.UserActivity;

/**
 * Class description:
 * 用户Fragment
 *
 * @author zp
 * @version 1.0
 * @see HomeUserFragment
 * @since 2019-05-09
 */
public class HomeUserFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View head = view.findViewById(R.id.home_user_head);
        head.setOnClickListener(this);

        View setting = view.findViewById(R.id.home_user_setting);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.home_user_head:
                UserActivity.go(requireContext());
                break;
            case R.id.home_user_setting:
                SettingActivity.go(requireContext());
                break;
        }
    }
}
