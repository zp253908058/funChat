package com.swpu.funchat.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see BaseFragment
 * @since 2019-05-09
 */
public abstract class BaseFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = mViewHolder.getItemView();
        if (view == null) {
            return inflater.inflate(getLayoutResId(), container, false);
        }
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (mViewHolder.getItemView() == null) {
            mViewHolder.setItemView(view);
            initView();
        }
    }

    protected void initView(){

    }

    protected <V extends View> V findViewById(@IdRes int idRes) {
        return mViewHolder.get(idRes);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewHolder.clear();
    }
}
