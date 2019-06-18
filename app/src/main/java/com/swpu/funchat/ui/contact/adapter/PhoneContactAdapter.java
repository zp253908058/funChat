package com.swpu.funchat.ui.contact.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.RecyclerAdapter;
import com.swpu.funchat.base.RecyclerViewHolder;
import com.swpu.funchat.model.PhoneContactEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see PhoneContactAdapter
 * @since 2019-06-18
 */
public class PhoneContactAdapter extends RecyclerAdapter<PhoneContactEntity> {
    public PhoneContactAdapter(@Nullable List<PhoneContactEntity> items) {
        super(items);
    }

    @Override
    protected int getLayoutByViewType(int viewType) {
        return R.layout.item_contact_add;
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, int position, PhoneContactEntity item) {
        TextView name=holder.get(R.id.contact_add_name);
        TextView number=holder.get(R.id.contact_add_phone);
        if(item!=null){
            name.setText(item.getPhoneContactName());
            number.setText(item.getPhoneContactNumber());
        }
    }

}

