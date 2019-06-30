package com.swpu.funchat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.swpu.funchat.R;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ImageFolderView
 * @since 2019-06-27
 */
public class ImageFolderView extends LinearLayout {


    public ImageFolderView(Context context) {
        this(context, null);
    }

    public ImageFolderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageFolderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View child = LayoutInflater.from(context).inflate(R.layout.fragment_login, this, false);
    }
}
