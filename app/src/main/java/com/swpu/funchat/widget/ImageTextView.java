package com.swpu.funchat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.swpu.funchat.R;

public class ImageTextView extends LinearLayoutCompat {

    private static final String TAG = ImageTextView.class.getSimpleName();

    private ImageView mIconView;

    private TextView mTextView;

    public ImageTextView(Context context) {
        this(context, null);
    }

    public ImageTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.setOrientation(HORIZONTAL);
        mIconView = new RadiusImageView(context);
        mIconView.setContentDescription(null);
        mIconView.setScaleType(ImageView.ScaleType.FIT_XY);
        LayoutParams iconParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iconParams.gravity = Gravity.CENTER_VERTICAL;
        int padding = context.getResources().getDimensionPixelOffset(R.dimen.inner_padding);
        mIconView.setPadding(padding, padding, padding, padding);
        addView(mIconView, iconParams);

        mTextView = new TextView(context);
        LayoutParams textParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        textParams.weight = 1;
        mTextView.setGravity(Gravity.CENTER_VERTICAL);
        int margin = context.getResources().getDimensionPixelOffset(R.dimen.normal_margin);
        textParams.setMarginStart(margin);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        addView(mTextView, textParams);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ImageTextView, defStyleAttr, 0);
        if (ta.hasValue(R.styleable.ImageTextView_android_src)) {
            mIconView.setImageDrawable(ta.getDrawable(R.styleable.ImageTextView_android_src));
        }
        if (ta.hasValue(R.styleable.ImageTextView_android_tint)) {
            mIconView.setImageTintList(ta.getColorStateList(R.styleable.ImageTextView_android_tint));
        }
        if (ta.hasValue(R.styleable.ImageTextView_image_height)) {
            iconParams.height = ta.getDimensionPixelOffset(R.styleable.ImageTextView_image_height, iconParams.height);
        }
        if (ta.hasValue(R.styleable.ImageTextView_image_width)) {
            iconParams.width = ta.getDimensionPixelOffset(R.styleable.ImageTextView_image_width, iconParams.width);
        }
        if (ta.hasValue(R.styleable.ImageTextView_android_text)) {
            mTextView.setText(ta.getString(R.styleable.ImageTextView_android_text));
        }
        if (ta.hasValue(R.styleable.ImageTextView_android_textColor)) {
            mTextView.setTextColor(ta.getColor(R.styleable.ImageTextView_android_textColor, Color.BLACK));
        }
        boolean isShowIndicator = ta.getBoolean(R.styleable.ImageTextView_isShowIndicator, false);
        if (isShowIndicator) {
            ImageView indicator = new ImageView(context);
            LayoutParams indicatorParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            indicator.setImageResource(R.drawable.ic_indicator_arrow_right);
            indicator.setScaleType(ImageView.ScaleType.CENTER);
            indicator.setImageTintList(ColorStateList.valueOf(context.getColor(R.color.icon_tint_color)));
            addView(indicator, indicatorParams);
        }
        ta.recycle();
    }

    @Override
    public void setOrientation(int orientation) {

    }

    public ImageView getIconView() {
        return mIconView;
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }
}
