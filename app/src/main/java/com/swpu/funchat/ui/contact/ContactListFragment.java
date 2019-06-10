package com.swpu.funchat.ui.contact;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.ui.chat.adapter.ContactAdapter;
import com.swpu.funchat.vm.ContactViewModel;
import com.swpu.funchat.widget.IndexBar;

import java.util.List;

/**
 * Class description:
 * 联系人Fragment
 *
 * @author zp
 * @version 1.0
 * @see ContactListFragment
 * @since 2019-05-09
 */
public class ContactListFragment extends NavigationFragment implements View.OnClickListener, IndexBar.OnTouchingLetterChangedListener {

    private static final String TAG = ContactListFragment.class.getSimpleName();

    public static ContactListFragment newInstance() {
        return new ContactListFragment();
    }

    private LinearLayoutManager mLayoutManager;
    private ContactAdapter mAdapter;
    private ContactViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(ContactViewModel.class);
        mAdapter = new ContactAdapter(null);
        mViewModel.startContactsLoading();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_contact;
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        TextView indicator = findViewById(R.id.home_contact_indicator);
        IndexBar indexBar = findViewById(R.id.home_contact_index);
        indexBar.setTextView(indicator);
        indexBar.setOnTouchingLetterChangedListener(this);

        View add = findViewById(R.id.home_contact_add);
        add.setOnClickListener(this);
        View groupChat = findViewById(R.id.home_contact_group_chat);
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
                navigate(R.id.action_home_fragment_to_contactAddFragment);
                break;
            case R.id.home_contact_group_chat:
                navigate(R.id.action_home_fragment_to_groupFragment);
                break;
        }
    }
}
