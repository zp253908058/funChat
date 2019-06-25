package com.swpu.funchat.ui.image.adapter;

import android.net.Uri;
import android.provider.Settings;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.swpu.funchat.R;
import com.swpu.funchat.base.RecyclerAdapter;
import com.swpu.funchat.base.RecyclerViewHolder;
import com.swpu.funchat.model.ImageEntity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ImageAdapter
 * @since 2019/6/24
 */
public class ImageAdapter extends RecyclerAdapter<ImageEntity> {

    @Override
    protected int getLayoutByViewType(int viewType) {
        return R.layout.item_image;
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, int position, ImageEntity item) {
        ImageView imageView = holder.get(R.id.radius_image_view);
        if (item != null) {
            Glide.with(holder.itemView).load(item.getPath()).into(imageView);
        }
    }
}
