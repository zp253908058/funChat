package com.swpu.funchat.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;
import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.ui.home.adapter.ContactAdapter;
import com.swpu.funchat.ui.home.decoration.ContactDecoration;
import com.swpu.funchat.vm.ContactViewModel;

import java.util.List;

/**
 * Class description:
 * 联系人Fragment
 *
 * @author zp
 * @version 1.0
 * @see HomeContactFragment
 * @since 2019-05-09
 */
public class HomeContactFragment extends BaseFragment {

    private ContactAdapter mAdapter;
    private ContactViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(ContactViewModel.class);
        mAdapter = new ContactAdapter(null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new ContactDecoration(requireContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Observer<List<ContactEntity>> observer = contactEntities -> mAdapter.setItems(contactEntities);
        mViewModel.getContactsLiveData().observe(this, observer);
    }
}
