package com.swpu.funchat.ui.image.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.RecyclerAdapter;
import com.swpu.funchat.base.RecyclerViewHolder;
import com.swpu.funchat.model.FolderEntity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see FolderAdapter
 * @since 2019/6/24
 */
public class FolderAdapter extends RecyclerAdapter<FolderEntity> {

    @Override
    protected int getLayoutByViewType(int viewType) {
        return R.layout.item_folder;
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, int position, FolderEntity item) {
        TextView name = holder.get(R.id.folder_name);
        ImageView image = holder.get(R.id.folder_image);
        if (item != null) {

        }

    }
}
