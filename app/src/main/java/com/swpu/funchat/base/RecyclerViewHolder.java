package com.swpu.funchat.base;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see RecyclerViewHolder
 * @since 2019-05-10
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private ViewHolder mViewHolder;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        mViewHolder = new ViewHolder(itemView);
    }

    public <T extends View> T get(@IdRes int resId) {
        return mViewHolder.get(resId);
    }

}
