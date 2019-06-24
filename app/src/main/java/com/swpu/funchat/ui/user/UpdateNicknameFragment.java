package com.swpu.funchat.ui.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.vm.UserViewModel;

/**
 * Class description:
 * 修改昵称界面
 *
 * @author zp
 * @version 1.0
 * @see UpdateNicknameFragment
 * @since 2019-05-10
 */
public class UpdateNicknameFragment extends NavigationFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_update_nickname;
    }

    private UserViewModel mUserViewModel;
    private String mNickname;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
        mUserViewModel.addOnResponseListener(UserViewModel.KEY_UPDATE_NICKNAME_RESPONSE_LISTENER, this::onResponse);
    }

    @Override
    protected void initView() {
        EditText nickname = findViewById(R.id.update_nickname);
        View view = findViewById(R.id.update_save_button);
        view.setOnClickListener(v -> save());
        mUserViewModel.getUserObservable().observe(this, userEntity -> {
            if (userEntity == null) {
                return;
            }
            mNickname = userEntity.getNickname();
            nickname.setText(mNickname);
            nickname.setSelection(mNickname.length());
        });
        nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                View save = findViewById(R.id.update_save_button);
                String nickname = s.toString();
                if (!TextUtils.isEmpty(nickname) && !nickname.equals(mNickname)) {
                    save.setEnabled(true);
                }
            }
        });
    }

    private void save() {
        EditText nicknameText = findViewById(R.id.update_nickname);
        String nickname = nicknameText.getText().toString();
        mUserViewModel.setUserNickname(nickname);
    }

    private void onResponse() {
        getNavController().navigateUp();
    }
}
