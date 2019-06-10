package com.swpu.funchat.ui.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;

/**
 * Class description:
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mNavController = Navigation.findNavController(view);
        TextView register = view.findViewById(R.id.login_register);
//        register.setOnClickListener(v -> mNavController.navigate(R.id.action_loginFragment_to_registerFragment));
    }
}
