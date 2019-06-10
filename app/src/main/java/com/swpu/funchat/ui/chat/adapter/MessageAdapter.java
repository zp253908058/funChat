package com.swpu.funchat.ui.chat.adapter;

import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.base.RecyclerAdapter;
import com.swpu.funchat.base.RecyclerViewHolder;
import com.swpu.funchat.model.MessageEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see MessageAdapter
 * @since 2019-06-06
 */
public class MessageAdapter extends RecyclerAdapter<MessageEntity> {
    /**
     * the constructor of this class.
     *
     * @param items the data source.
     */
    public MessageAdapter(@Nullable List<MessageEntity> items) {
        super(items);
    }

    @Override
    protected int getLayoutByViewType(int viewType) {
        return R.layout.item_message;
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, int position, MessageEntity item) {

    }
}
