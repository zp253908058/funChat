package com.swpu.funchat.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.base.RecyclerViewFragment;
import com.swpu.funchat.model.MessageEntity;
import com.swpu.funchat.ui.home.adapter.MessageAdapter;
import com.swpu.funchat.vm.MessageViewModel;

/**
 * Class description:
 * 消息Fragment
 *
 * @author zp
 * @version 1.0
 * @see HomeMessageFragment
 * @since 2019-05-09
 */
public class HomeMessageFragment extends RecyclerViewFragment {

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
        mAdapter = new MessageAdapter(null);
        view.setAdapter(mAdapter);
    }
}
