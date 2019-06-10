package com.swpu.funchat.ui.chat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;

/**
 * Class description:
 * 聊天界面
 *
 * @author zp
 * @version 1.0
 * @see ChatFragment
 * @since 2019-06-06
 */
public class ChatFragment extends NavigationFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_more, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_more) {
            navigate(R.id.action_chatFragment_to_chatInfoFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
