package com.swpu.funchat.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.R;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see RecyclerViewFragment
 * @since 2019-05-10
 */
public abstract class RecyclerViewFragment extends NavigationFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.common_refresh_recycler_view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        onRecyclerViewCreated(recyclerView);
    }

    protected abstract void onRecyclerViewCreated(RecyclerView view);
}
