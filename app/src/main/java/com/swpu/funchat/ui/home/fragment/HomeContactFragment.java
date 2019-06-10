package com.swpu.funchat.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;
import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.ui.contact.ContactAddActivity;
import com.swpu.funchat.ui.home.adapter.ContactAdapter;
import com.swpu.funchat.vm.ContactViewModel;
import com.swpu.funchat.widget.IndexBar;

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
public class HomeContactFragment extends BaseFragment implements View.OnClickListener, IndexBar.OnTouchingLetterChangedListener {

    private LinearLayoutManager mLayoutManager;
    private ContactAdapter mAdapter;
    private ContactViewModel mViewModel;
    private IndexBar mIndexBar;

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
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        TextView indicator = view.findViewById(R.id.home_contact_indicator);
        mIndexBar = view.findViewById(R.id.home_contact_index);
        mIndexBar.setTextView(indicator);
        mIndexBar.setOnTouchingLetterChangedListener(this);

        View add = view.findViewById(R.id.home_contact_add);
        add.setOnClickListener(this);
        View groupChat = view.findViewById(R.id.home_contact_group_chat);
        groupChat.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Observer<List<ContactEntity>> observer = contactEntities -> mAdapter.setItems(contactEntities);
        mViewModel.getContactsLiveData().observe(this, observer);
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        //该字母首次出现的位置
        int position = mAdapter.getPositionForSection(s.charAt(0));
        if (position != -1) {
            mLayoutManager.scrollToPositionWithOffset(position, 0);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.home_contact_add:
                ContactAddActivity.go(requireContext());
                break;
            case R.id.home_contact_group_chat:
                break;
        }
    }
}
