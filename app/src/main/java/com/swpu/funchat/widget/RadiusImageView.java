package com.swpu.funchat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

import com.swpu.funchat.R;

public class RadiusImageView extends AppCompatImageView {

    private static final String TAG = RadiusImageView.class.getSimpleName();
    //圆角半径
    private int mRadius = 0;
    private int mLeftTopRadius;
    private int mRightTopRadius;
    private int mRightBottomRadius;
    private int mLeftBottomRadius;
    private Path mPath = new Path();

    public RadiusImageView(Context context) {
        this(context, null);
    }

    public RadiusImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadiusImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 逻辑：如果mRadius 的值不为0，则只使用mRadius的值
     * 否则使用其他四个圆角的半径
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr def
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RadiusImageView, defStyleAttr, 0);
        if (ta.hasValue(R.styleable.RadiusImageView_radius)) {
            mRadius = ta.getDimensionPixelOffset(R.styleable.RadiusImageView_radius, 0);
        }
        if (mRadius == 0) {
            mLeftTopRadius = ta.getDimensionPixelOffset(R.styleable.RadiusImageView_left_topRadius, 0);
            mRightTopRadius = ta.getDimensionPixelOffset(R.styleable.RadiusImageView_right_topRadius, 0);
            mLeftBottomRadius = ta.getDimensionPixelOffset(R.styleable.RadiusImageView_left_bottomRadius, 0);
            mRightBottomRadius = ta.getDimensionPixelOffset(R.styleable.RadiusImageView_right_bottomRadius, 0);
        } else {
            mLeftTopRadius = mRadius;
            mRightTopRadius = mRadius;
            mRightBottomRadius = mRadius;
            mLeftBottomRadius = mRadius;
        }
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //如果没有设置圆角，什么都不做
        if (mLeftTopRadius == 0 && mRightTopRadius == 0 && mLeftBottomRadius == 0 && mRightBottomRadius == 0) {
            super.onDraw(canvas);
            return;
        }

        //这里做下判断，只有图片的宽高大于设置的圆角距离的时候才进行裁剪
        int maxLeft = Math.max(mLeftTopRadius, mLeftBottomRadius);
        int maxRight = Math.max(mRightTopRadius, mRightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(mLeftTopRadius, mRightTopRadius);
        int maxBottom = Math.max(mLeftBottomRadius, mRightBottomRadius);
        int minHeight = maxTop + maxBottom;

        int top = getPaddingTop();
        int right = getPaddingRight();
        int bottom = getPaddingBottom();
        int left = getPaddingLeft();
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //绘制的实际宽度为控件宽度减去左右padding
        int realWidth = width - left - right;
        //绘制的实际高度为控件宽度减去上下padding
        int realHeight = height - top - bottom;
        Log.e(TAG, "minWidth = " + minWidth + ", minHeight = " + minHeight + ", realWidth = " + realWidth + ", realHeight = " + realHeight + ", width = " + width + ", height = " + height);
        if (realWidth >= minWidth && realHeight >= minHeight) {
            mPath.reset();
            //四个角：右上，右下，左下，左上
            //定义路径的起点
            float startX = mLeftTopRadius + getPaddingLeft();
            float startY = getPaddingTop();
            mPath.moveTo(startX, startY);
            mPath.rLineTo(realWidth - mLeftTopRadius - mRightTopRadius, 0);
            mPath.rQuadTo(mRightTopRadius, 0, mRightTopRadius, mRightTopRadius);

            mPath.rLineTo(0, realHeight - mRightTopRadius - mRightBottomRadius);
            mPath.rQuadTo(0, mRightBottomRadius, -mRightBottomRadius, mRightBottomRadius);

            mPath.rLineTo(-(realWidth - mRightBottomRadius - mLeftBottomRadius), 0);
            mPath.rQuadTo(-mLeftBottomRadius, 0, -mLeftBottomRadius, -mLeftBottomRadius);

            mPath.rLineTo(0, -(realHeight - mLeftBottomRadius - mLeftTopRadius));
            mPath.rQuadTo(0, -mLeftTopRadius, mLeftTopRadius, -mLeftTopRadius);

            canvas.clipPath(mPath);
            canvas.save();
        }
        super.onDraw(canvas);
    }
}
