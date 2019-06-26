package com.swpu.funchat.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.LayoutRes;

import com.swpu.funchat.util.CollectionsUtils;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ListBaseAdapter
 * @since 2019/6/26
 */
public abstract class ListBaseAdapter<T> extends BaseAdapter {

    private List<T> mData;

    public ListBaseAdapter() {
        this(null);
    }

    public ListBaseAdapter(List<T> data) {
        mData = data;
    }

    public void setData(List<T> data) {
        mData = data;
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        return CollectionsUtils.sizeOf(mData);
    }

    @Override
    public T getItem(int position) {
        return CollectionsUtils.get(mData, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(getItemViewType(position)), parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        obtainData(holder, position, getItem(position));
        return convertView;
    }

    protected abstract void obtainData(ViewHolder holder, int position, T item);

    @LayoutRes
    protected abstract int getLayoutId(int type);
}
