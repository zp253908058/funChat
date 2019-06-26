package com.swpu.funchat.ui.image.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.ListBaseAdapter;
import com.swpu.funchat.base.ViewHolder;
import com.swpu.funchat.model.FolderEntity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see FolderAdapter
 * @since 2019/6/24
 */
public class FolderAdapter extends ListBaseAdapter<FolderEntity> {


    @Override
    protected void obtainData(ViewHolder holder, int position, FolderEntity item) {
        TextView name = holder.get(R.id.folder_name);
        ImageView image = holder.get(R.id.folder_image);
        if (item != null) {

        }
    }

    @Override
    protected int getLayoutId(int type) {
        return R.layout.item_folder;
    }
}
