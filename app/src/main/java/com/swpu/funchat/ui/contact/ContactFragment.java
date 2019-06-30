package com.swpu.funchat.ui.contact;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.model.ContactEntity;

/**
 * Class description:
 * 联系人信息界面
 *
 * @author zp
 * @version 1.0
 * @see ContactFragment
 * @since 2019-05-09
 */
public class ContactFragment extends NavigationFragment implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View contactNoteLayout = view.findViewById(R.id.contact_note_layout);
        contactNoteLayout.setOnClickListener(this);
        View contactMoreInformationLayout = view.findViewById(R.id.contact_more_information_layout);
        contactMoreInformationLayout.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_contact;
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
            navigate(R.id.action_contactFragment_to_contactInfoSetFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.contact_note_layout:
                setNote();
                break;
            case R.id.contact_more_information_layout:
                checkMoreInfo();
                break;
        }
    }

    private void setNote() {
        navigate(R.id.action_contactFragment_to_contactNoteFragment);
    }

    private void checkMoreInfo() {
        navigate(R.id.action_contactFragment_to_contactMoreInfoFragment);
    }

    public void initView() {
        TextView mTextView = findViewById(R.id.contact_user_name);
        Bundle bundle = getArguments();
        if (bundle != null) {
            ContactEntity entity = (ContactEntity) bundle.getSerializable("contact");
            if (entity != null) {
                String name = entity.getName();
                mTextView.setText(name);
            }
        }
    }
}

