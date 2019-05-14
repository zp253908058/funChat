package com.swpu.funchat.ui.home.adapter;

import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.RecyclerAdapter;
import com.swpu.funchat.base.RecyclerViewHolder;
import com.swpu.funchat.model.ContactEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ContactAdapter
 * @since 2019-05-14
 */
public class ContactAdapter extends RecyclerAdapter<ContactEntity> {
    /**
     * the constructor of this class.
     *
     * @param items the data source.
     */
    public ContactAdapter(@Nullable List<ContactEntity> items) {
        super(items);
    }

    @Override
    protected int getLayoutByViewType(int viewType) {
        return R.layout.item_contact_layout;
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, int position, ContactEntity item) {

    }
}
