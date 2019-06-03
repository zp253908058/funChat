package com.swpu.funchat.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;

/**
 * Class description:
 * 用户Fragment
 *
 * @author zp
 * @version 1.0
 * @see HomeUserFragment
 * @since 2019-05-09
 */
public class HomeUserFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_user, container, false);
    }
}
