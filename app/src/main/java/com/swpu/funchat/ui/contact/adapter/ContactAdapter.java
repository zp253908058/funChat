package com.swpu.funchat.ui.contact.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.swpu.funchat.R;
import com.swpu.funchat.base.RecyclerAdapter;
import com.swpu.funchat.base.RecyclerViewHolder;
import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.util.PinyinUtils;
import com.swpu.funchat.widget.ImageTextView;

import java.util.List;

/**
 * Class description:
 * 联系人列表适配器
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
        return R.layout.item_contact;
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, int position, ContactEntity item) {
        TextView tag = holder.get(R.id.contact_tag);
        ImageTextView contact = holder.get(R.id.contact_contact);

        int section = getSectionForPosition(position);
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            tag.setVisibility(View.VISIBLE);
            String name = item != null ? item.getName() : "";
            tag.setText(String.valueOf(PinyinUtils.getFirstSpell(name).toUpperCase().charAt(0)));
        } else {
            tag.setVisibility(View.GONE);
        }

        if (item != null) {
            contact.setText(item.getName());
            Glide.with(holder.itemView).load(item.getAvatar()).into(contact.getIconView());
        }
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        ContactEntity entity = getItem(position);
        String pinyin = PinyinUtils.getPingYin(entity.getName());
        return pinyin.toUpperCase().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            ContactEntity entity = getItem(i);
            char firstChar = PinyinUtils.getFirstSpell(entity.getName()).toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
