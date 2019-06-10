package com.swpu.funchat.ui.chat;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.RecyclerViewFragment;
import com.swpu.funchat.base.RecyclerViewTouchListener;
import com.swpu.funchat.model.MessageEntity;
import com.swpu.funchat.ui.chat.adapter.MessageAdapter;
import com.swpu.funchat.vm.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description:
 * 首页消息界面
 *
 * @author zp
 * @version 1.0
 * @see MessageFragment
 * @since 2019-05-09
 */
public class MessageFragment extends RecyclerViewFragment implements RecyclerViewTouchListener.OnItemClickListener {

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    private MessageAdapter mAdapter;
    private MessageViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(MessageViewModel.class);
    }

    @Override
    protected void onRecyclerViewCreated(RecyclerView view) {
        view.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        view.setItemAnimator(new DefaultItemAnimator());
        view.setLayoutManager(new LinearLayoutManager(requireContext()));
        view.addOnItemTouchListener(new RecyclerViewTouchListener(requireContext(), this));
        List<MessageEntity> items = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            items.add(new MessageEntity());
        }
        mAdapter = new MessageAdapter(items);
        view.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        navigate(R.id.action_home_fragment_to_chatFragment);
    }
}
