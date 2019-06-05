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
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.swpu.funchat.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ImageTextView extends LinearLayoutCompat {

    private static final String TAG = ImageTextView.class.getSimpleName();
    private static final ScaleType[] sScaleTypeArray = {
            ScaleType.MATRIX,
            ScaleType.FIT_XY,
            ScaleType.FIT_START,
            ScaleType.FIT_CENTER,
            ScaleType.FIT_END,
            ScaleType.CENTER,
            ScaleType.CENTER_CROP,
            ScaleType.CENTER_INSIDE
    };

    private RadiusImageView mIconView;

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
        mIconView.setScaleType(ScaleType.CENTER_INSIDE);
        LayoutParams iconParams = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        iconParams.gravity = Gravity.CENTER_VERTICAL;
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
        if (ta.hasValue(R.styleable.ImageTextView_android_scaleType)) {
            int index = ta.getInt(R.styleable.ImageTextView_android_scaleType, -1);
            if (index >= 0) {
                mIconView.setScaleType(sScaleTypeArray[index]);
            }
        }
        if (ta.hasValue(R.styleable.ImageTextView_image_side)) {
            iconParams.height = ta.getDimensionPixelOffset(R.styleable.ImageTextView_image_side, iconParams.height);
            iconParams.width = ta.getDimensionPixelOffset(R.styleable.ImageTextView_image_side, iconParams.width);
        }
        if (ta.hasValue(R.styleable.ImageTextView_image_padding)) {
            int padding = ta.getDimensionPixelOffset(R.styleable.ImageTextView_image_padding, 0);
            mIconView.setPadding(padding, padding, padding, padding);
        }
        if (ta.hasValue(R.styleable.ImageTextView_radius)) {
            mIconView.setRadius(ta.getDimensionPixelOffset(R.styleable.ImageTextView_radius, 0));
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
            LayoutParams indicatorParams = new LayoutParams(WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            indicator.setImageResource(R.drawable.ic_indicator_arrow_right);
            indicator.setScaleType(ImageView.ScaleType.CENTER);
            indicator.setImageTintList(ColorStateList.valueOf(context.getColor(R.color.icon_tint_color)));
            addView(indicator, indicatorParams);
        }
        ta.recycle();
    }

    @Override
    public void setOrientation(int orientation) {
        int side = 0;

    }

    public ImageView getIconView() {
        return mIconView;
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }
}
