package com.swpu.funchat.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;

/**
 * Class description:
 * 消息Fragment
 *
 * @author zp
 * @version 1.0
 * @see MessageFragment
 * @since 2019-05-09
 */
public class MessageFragment extends BaseFragment {

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

}
