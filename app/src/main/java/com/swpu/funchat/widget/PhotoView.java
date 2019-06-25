package com.swpu.funchat.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.OverScroller;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see PhotoView
 * @since 2019/6/24
 */
public class PhotoView extends AppCompatImageView {

    private PhotoViewAttacher attacher;
    private ScaleType pendingScaleType;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public PhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init();
    }

    private void init() {
        attacher = new PhotoViewAttacher(this);
        //We always pose as a Matrix scale type, though we can change to another scale type
        //via the attacher
        super.setScaleType(ScaleType.MATRIX);
        //apply the previously applied scale type
        if (pendingScaleType != null) {
            setScaleType(pendingScaleType);
            pendingScaleType = null;
        }
    }

    /**
     * Get the current {@link PhotoViewAttacher} for this view. Be wary of holding on to references
     * to this attacher, as it has a reference to this view, which, if a reference is held in the
     * wrong place, can cause memory leaks.
     *
     * @return the attacher.
     */
    public PhotoViewAttacher getAttacher() {
        return attacher;
    }

    @Override
    public ScaleType getScaleType() {
        return attacher.getScaleType();
    }

    @Override
    public Matrix getImageMatrix() {
        return attacher.getImageMatrix();
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        attacher.setOnLongClickListener(l);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        attacher.setOnClickListener(l);
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (attacher == null) {
            pendingScaleType = scaleType;
        } else {
            attacher.setScaleType(scaleType);
        }
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        // setImageBitmap calls through to this method
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        boolean changed = super.setFrame(l, t, r, b);
        if (changed) {
            attacher.update();
        }
        return changed;
    }

    public void setRotationTo(float rotationDegree) {
        attacher.setRotationTo(rotationDegree);
    }

    public void setRotationBy(float rotationDegree) {
        attacher.setRotationBy(rotationDegree);
    }

    public boolean isZoomable() {
        return attacher.isZoomable();
    }

    public void setZoomable(boolean zoomable) {
        attacher.setZoomable(zoomable);
    }

    public RectF getDisplayRect() {
        return attacher.getDisplayRect();
    }

    public void getDisplayMatrix(Matrix matrix) {
        attacher.getDisplayMatrix(matrix);
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean setDisplayMatrix(Matrix finalRectangle) {
        return attacher.setDisplayMatrix(finalRectangle);
    }

    public void getSuppMatrix(Matrix matrix) {
        attacher.getSuppMatrix(matrix);
    }

    public boolean setSuppMatrix(Matrix matrix) {
        return attacher.setDisplayMatrix(matrix);
    }

    public float getMinimumScale() {
        return attacher.getMinimumScale();
    }

    public float getMediumScale() {
        return attacher.getMediumScale();
    }

    public float getMaximumScale() {
        return attacher.getMaximumScale();
    }

    public float getScale() {
        return attacher.getScale();
    }

    public void setAllowParentInterceptOnEdge(boolean allow) {
        attacher.setAllowParentInterceptOnEdge(allow);
    }

    public void setMinimumScale(float minimumScale) {
        attacher.setMinimumScale(minimumScale);
    }

    public void setMediumScale(float mediumScale) {
        attacher.setMediumScale(mediumScale);
    }

    public void setMaximumScale(float maximumScale) {
        attacher.setMaximumScale(maximumScale);
    }

    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        attacher.setScaleLevels(minimumScale, mediumScale, maximumScale);
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
        attacher.setOnMatrixChangeListener(listener);
    }

    public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        attacher.setOnPhotoTapListener(listener);
    }

    public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener listener) {
        attacher.setOnOutsidePhotoTapListener(listener);
    }

    public void setOnViewTapListener(OnViewTapListener listener) {
        attacher.setOnViewTapListener(listener);
    }

    public void setOnViewDragListener(OnViewDragListener listener) {
        attacher.setOnViewDragListener(listener);
    }

    public void setScale(float scale) {
        attacher.setScale(scale);
    }

    public void setScale(float scale, boolean animate) {
        attacher.setScale(scale, animate);
    }

    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        attacher.setScale(scale, focalX, focalY, animate);
    }

    public void setZoomTransitionDuration(int milliseconds) {
        attacher.setZoomTransitionDuration(milliseconds);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        attacher.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnScaleChangeListener(OnScaleChangedListener onScaleChangedListener) {
        attacher.setOnScaleChangeListener(onScaleChangedListener);
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        attacher.setOnSingleFlingListener(onSingleFlingListener);
    }

    public static class PhotoViewAttacher implements View.OnTouchListener,
            View.OnLayoutChangeListener {

        private static float DEFAULT_MAX_SCALE = 3.0f;
        private static float DEFAULT_MID_SCALE = 1.75f;
        private static float DEFAULT_MIN_SCALE = 1.0f;
        private static int DEFAULT_ZOOM_DURATION = 200;

        private static final int HORIZONTAL_EDGE_NONE = -1;
        private static final int HORIZONTAL_EDGE_LEFT = 0;
        private static final int HORIZONTAL_EDGE_RIGHT = 1;
        private static final int HORIZONTAL_EDGE_BOTH = 2;
        private static final int VERTICAL_EDGE_NONE = -1;
        private static final int VERTICAL_EDGE_TOP = 0;
        private static final int VERTICAL_EDGE_BOTTOM = 1;
        private static final int VERTICAL_EDGE_BOTH = 2;
        private static int SINGLE_TOUCH = 1;

        private Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
        private int mZoomDuration = DEFAULT_ZOOM_DURATION;
        private float mMinScale = DEFAULT_MIN_SCALE;
        private float mMidScale = DEFAULT_MID_SCALE;
        private float mMaxScale = DEFAULT_MAX_SCALE;

        private boolean mAllowParentInterceptOnEdge = true;
        private boolean mBlockParentIntercept = false;

        private ImageView mImageView;

        // Gesture Detectors
        private GestureDetector mGestureDetector;
        private CustomGestureDetector mScaleDragDetector;

        // These are set so we don't keep allocating them on the heap
        private final Matrix mBaseMatrix = new Matrix();
        private final Matrix mDrawMatrix = new Matrix();
        private final Matrix mSuppMatrix = new Matrix();
        private final RectF mDisplayRect = new RectF();
        private final float[] mMatrixValues = new float[9];

        // Listeners
        private OnMatrixChangedListener mMatrixChangeListener;
        private OnPhotoTapListener mPhotoTapListener;
        private OnOutsidePhotoTapListener mOutsidePhotoTapListener;
        private OnViewTapListener mViewTapListener;
        private View.OnClickListener mOnClickListener;
        private OnLongClickListener mLongClickListener;
        private OnScaleChangedListener mScaleChangeListener;
        private OnSingleFlingListener mSingleFlingListener;
        private OnViewDragListener mOnViewDragListener;

        private FlingRunnable mCurrentFlingRunnable;
        private int mHorizontalScrollEdge = HORIZONTAL_EDGE_BOTH;
        private int mVerticalScrollEdge = VERTICAL_EDGE_BOTH;
        private float mBaseRotation;

        private boolean mZoomEnabled = true;
        private ScaleType mScaleType = ScaleType.FIT_CENTER;

        private OnGestureListener onGestureListener = new OnGestureListener() {
            @Override
            public void onDrag(float dx, float dy) {
                if (mScaleDragDetector.isScaling()) {
                    return; // Do not drag if we are already scaling
                }
                if (mOnViewDragListener != null) {
                    mOnViewDragListener.onDrag(dx, dy);
                }
                mSuppMatrix.postTranslate(dx, dy);
                checkAndDisplayMatrix();

                /*
                 * Here we decide whether to let the ImageView's parent to start taking
                 * over the touch event.
                 *
                 * First we check whether this function is enabled. We never want the
                 * parent to take over if we're scaling. We then check the edge we're
                 * on, and the direction of the scroll (i.e. if we're pulling against
                 * the edge, aka 'overscrolling', let the parent take over).
                 */
                ViewParent parent = mImageView.getParent();
                if (mAllowParentInterceptOnEdge && !mScaleDragDetector.isScaling() && !mBlockParentIntercept) {
                    if (mHorizontalScrollEdge == HORIZONTAL_EDGE_BOTH
                            || (mHorizontalScrollEdge == HORIZONTAL_EDGE_LEFT && dx >= 1f)
                            || (mHorizontalScrollEdge == HORIZONTAL_EDGE_RIGHT && dx <= -1f)
                            || (mVerticalScrollEdge == VERTICAL_EDGE_TOP && dy >= 1f)
                            || (mVerticalScrollEdge == VERTICAL_EDGE_BOTTOM && dy <= -1f)) {
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(false);
                        }
                    }
                } else {
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
            }

            @Override
            public void onFling(float startX, float startY, float velocityX, float velocityY) {
                mCurrentFlingRunnable = new FlingRunnable(mImageView.getContext());
                mCurrentFlingRunnable.fling(getImageViewWidth(mImageView),
                        getImageViewHeight(mImageView), (int) velocityX, (int) velocityY);
                mImageView.post(mCurrentFlingRunnable);
            }

            @Override
            public void onScale(float scaleFactor, float focusX, float focusY) {
                if (getScale() < mMaxScale || scaleFactor < 1f) {
                    if (mScaleChangeListener != null) {
                        mScaleChangeListener.onScaleChange(scaleFactor, focusX, focusY);
                    }
                    mSuppMatrix.postScale(scaleFactor, scaleFactor, focusX, focusY);
                    checkAndDisplayMatrix();
                }
            }
        };

        public PhotoViewAttacher(ImageView imageView) {
            mImageView = imageView;
            imageView.setOnTouchListener(this);
            imageView.addOnLayoutChangeListener(this);
            if (imageView.isInEditMode()) {
                return;
            }
            mBaseRotation = 0.0f;
            // Create Gesture Detectors...
            mScaleDragDetector = new CustomGestureDetector(imageView.getContext(), onGestureListener);
            mGestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() {

                // forward long click listener
                @Override
                public void onLongPress(MotionEvent e) {
                    if (mLongClickListener != null) {
                        mLongClickListener.onLongClick(mImageView);
                    }
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2,
                                       float velocityX, float velocityY) {
                    if (mSingleFlingListener != null) {
                        if (getScale() > DEFAULT_MIN_SCALE) {
                            return false;
                        }
                        if (e1.getPointerCount() > SINGLE_TOUCH
                                || e2.getPointerCount() > SINGLE_TOUCH) {
                            return false;
                        }
                        return mSingleFlingListener.onFling(e1, e2, velocityX, velocityY);
                    }
                    return false;
                }
            });
            mGestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(mImageView);
                    }
                    final RectF displayRect = getDisplayRect();
                    final float x = e.getX(), y = e.getY();
                    if (mViewTapListener != null) {
                        mViewTapListener.onViewTap(mImageView, x, y);
                    }
                    if (displayRect != null) {
                        // Check to see if the user tapped on the photo
                        if (displayRect.contains(x, y)) {
                            float xResult = (x - displayRect.left)
                                    / displayRect.width();
                            float yResult = (y - displayRect.top)
                                    / displayRect.height();
                            if (mPhotoTapListener != null) {
                                mPhotoTapListener.onPhotoTap(mImageView, xResult, yResult);
                            }
                            return true;
                        } else {
                            if (mOutsidePhotoTapListener != null) {
                                mOutsidePhotoTapListener.onOutsidePhotoTap(mImageView);
                            }
                        }
                    }
                    return false;
                }

                @Override
                public boolean onDoubleTap(MotionEvent ev) {
                    try {
                        float scale = getScale();
                        float x = ev.getX();
                        float y = ev.getY();
                        if (scale < getMediumScale()) {
                            setScale(getMediumScale(), x, y, true);
                        } else if (scale >= getMediumScale() && scale < getMaximumScale()) {
                            setScale(getMaximumScale(), x, y, true);
                        } else {
                            setScale(getMinimumScale(), x, y, true);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // Can sometimes happen when getX() and getY() is called
                    }
                    return true;
                }

                @Override
                public boolean onDoubleTapEvent(MotionEvent e) {
                    // Wait for the confirmed onDoubleTap() instead
                    return false;
                }
            });
        }

        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener newOnDoubleTapListener) {
            this.mGestureDetector.setOnDoubleTapListener(newOnDoubleTapListener);
        }

        public void setOnScaleChangeListener(OnScaleChangedListener onScaleChangeListener) {
            this.mScaleChangeListener = onScaleChangeListener;
        }

        public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
            this.mSingleFlingListener = onSingleFlingListener;
        }

        @Deprecated
        public boolean isZoomEnabled() {
            return mZoomEnabled;
        }

        public RectF getDisplayRect() {
            checkMatrixBounds();
            return getDisplayRect(getDrawMatrix());
        }

        public boolean setDisplayMatrix(Matrix finalMatrix) {
            if (finalMatrix == null) {
                throw new IllegalArgumentException("Matrix cannot be null");
            }
            if (mImageView.getDrawable() == null) {
                return false;
            }
            mSuppMatrix.set(finalMatrix);
            checkAndDisplayMatrix();
            return true;
        }

        public void setBaseRotation(final float degrees) {
            mBaseRotation = degrees % 360;
            update();
            setRotationBy(mBaseRotation);
            checkAndDisplayMatrix();
        }

        public void setRotationTo(float degrees) {
            mSuppMatrix.setRotate(degrees % 360);
            checkAndDisplayMatrix();
        }

        public void setRotationBy(float degrees) {
            mSuppMatrix.postRotate(degrees % 360);
            checkAndDisplayMatrix();
        }

        public float getMinimumScale() {
            return mMinScale;
        }

        public float getMediumScale() {
            return mMidScale;
        }

        public float getMaximumScale() {
            return mMaxScale;
        }

        public float getScale() {
            return (float) Math.sqrt((float) Math.pow(getValue(mSuppMatrix, Matrix.MSCALE_X), 2) + (float) Math.pow
                    (getValue(mSuppMatrix, Matrix.MSKEW_Y), 2));
        }

        public ScaleType getScaleType() {
            return mScaleType;
        }

        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int
                oldRight, int oldBottom) {
            // Update our base matrix, as the bounds have changed
            if (left != oldLeft || top != oldTop || right != oldRight || bottom != oldBottom) {
                updateBaseMatrix(mImageView.getDrawable());
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent ev) {
            boolean handled = false;
            if (mZoomEnabled && hasDrawable((ImageView) v)) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ViewParent parent = v.getParent();
                        // First, disable the Parent from intercepting the touch
                        // event
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                        // If we're flinging, and the user presses down, cancel
                        // fling
                        cancelFling();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        // If the user has zoomed less than min scale, zoom back
                        // to min scale
                        if (getScale() < mMinScale) {
                            RectF rect = getDisplayRect();
                            if (rect != null) {
                                v.post(new AnimatedZoomRunnable(getScale(), mMinScale,
                                        rect.centerX(), rect.centerY()));
                                handled = true;
                            }
                        } else if (getScale() > mMaxScale) {
                            RectF rect = getDisplayRect();
                            if (rect != null) {
                                v.post(new AnimatedZoomRunnable(getScale(), mMaxScale,
                                        rect.centerX(), rect.centerY()));
                                handled = true;
                            }
                        }
                        break;
                }
                // Try the Scale/Drag detector
                if (mScaleDragDetector != null) {
                    boolean wasScaling = mScaleDragDetector.isScaling();
                    boolean wasDragging = mScaleDragDetector.isDragging();
                    handled = mScaleDragDetector.onTouchEvent(ev);
                    boolean didntScale = !wasScaling && !mScaleDragDetector.isScaling();
                    boolean didntDrag = !wasDragging && !mScaleDragDetector.isDragging();
                    mBlockParentIntercept = didntScale && didntDrag;
                }
                // Check to see if the user double tapped
                if (mGestureDetector != null && mGestureDetector.onTouchEvent(ev)) {
                    handled = true;
                }

            }
            return handled;
        }

        public void setAllowParentInterceptOnEdge(boolean allow) {
            mAllowParentInterceptOnEdge = allow;
        }

        public void setMinimumScale(float minimumScale) {
            checkZoomLevels(minimumScale, mMidScale, mMaxScale);
            mMinScale = minimumScale;
        }

        public void setMediumScale(float mediumScale) {
            checkZoomLevels(mMinScale, mediumScale, mMaxScale);
            mMidScale = mediumScale;
        }

        public void setMaximumScale(float maximumScale) {
            checkZoomLevels(mMinScale, mMidScale, maximumScale);
            mMaxScale = maximumScale;
        }

        public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
            checkZoomLevels(minimumScale, mediumScale, maximumScale);
            mMinScale = minimumScale;
            mMidScale = mediumScale;
            mMaxScale = maximumScale;
        }

        public void setOnLongClickListener(OnLongClickListener listener) {
            mLongClickListener = listener;
        }

        public void setOnClickListener(View.OnClickListener listener) {
            mOnClickListener = listener;
        }

        public void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
            mMatrixChangeListener = listener;
        }

        public void setOnPhotoTapListener(OnPhotoTapListener listener) {
            mPhotoTapListener = listener;
        }

        public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener mOutsidePhotoTapListener) {
            this.mOutsidePhotoTapListener = mOutsidePhotoTapListener;
        }

        public void setOnViewTapListener(OnViewTapListener listener) {
            mViewTapListener = listener;
        }

        public void setOnViewDragListener(OnViewDragListener listener) {
            mOnViewDragListener = listener;
        }

        public void setScale(float scale) {
            setScale(scale, false);
        }

        public void setScale(float scale, boolean animate) {
            setScale(scale,
                    (mImageView.getRight()) / 2,
                    (mImageView.getBottom()) / 2,
                    animate);
        }

        public void setScale(float scale, float focalX, float focalY,
                             boolean animate) {
            // Check to see if the scale is within bounds
            if (scale < mMinScale || scale > mMaxScale) {
                throw new IllegalArgumentException("Scale must be within the range of minScale and maxScale");
            }
            if (animate) {
                mImageView.post(new AnimatedZoomRunnable(getScale(), scale,
                        focalX, focalY));
            } else {
                mSuppMatrix.setScale(scale, scale, focalX, focalY);
                checkAndDisplayMatrix();
            }
        }

        /**
         * Set the zoom interpolator
         *
         * @param interpolator the zoom interpolator
         */
        public void setZoomInterpolator(Interpolator interpolator) {
            mInterpolator = interpolator;
        }

        public void setScaleType(ScaleType scaleType) {
            if (isSupportedScaleType(scaleType) && scaleType != mScaleType) {
                mScaleType = scaleType;
                update();
            }
        }

        public boolean isZoomable() {
            return mZoomEnabled;
        }

        public void setZoomable(boolean zoomable) {
            mZoomEnabled = zoomable;
            update();
        }

        public void update() {
            if (mZoomEnabled) {
                // Update the base matrix using the current drawable
                updateBaseMatrix(mImageView.getDrawable());
            } else {
                // Reset the Matrix...
                resetMatrix();
            }
        }

        /**
         * Get the display matrix
         *
         * @param matrix target matrix to copy to
         */
        public void getDisplayMatrix(Matrix matrix) {
            matrix.set(getDrawMatrix());
        }

        /**
         * Get the current support matrix
         */
        public void getSuppMatrix(Matrix matrix) {
            matrix.set(mSuppMatrix);
        }

        private Matrix getDrawMatrix() {
            mDrawMatrix.set(mBaseMatrix);
            mDrawMatrix.postConcat(mSuppMatrix);
            return mDrawMatrix;
        }

        public Matrix getImageMatrix() {
            return mDrawMatrix;
        }

        public void setZoomTransitionDuration(int milliseconds) {
            this.mZoomDuration = milliseconds;
        }

        /**
         * Helper method that 'unpacks' a Matrix and returns the required value
         *
         * @param matrix     Matrix to unpack
         * @param whichValue Which value from Matrix.M* to return
         * @return returned value
         */
        private float getValue(Matrix matrix, int whichValue) {
            matrix.getValues(mMatrixValues);
            return mMatrixValues[whichValue];
        }

        /**
         * Resets the Matrix back to FIT_CENTER, and then displays its contents
         */
        private void resetMatrix() {
            mSuppMatrix.reset();
            setRotationBy(mBaseRotation);
            setImageViewMatrix(getDrawMatrix());
            checkMatrixBounds();
        }

        private void setImageViewMatrix(Matrix matrix) {
            mImageView.setImageMatrix(matrix);
            // Call MatrixChangedListener if needed
            if (mMatrixChangeListener != null) {
                RectF displayRect = getDisplayRect(matrix);
                if (displayRect != null) {
                    mMatrixChangeListener.onMatrixChanged(displayRect);
                }
            }
        }

        /**
         * Helper method that simply checks the Matrix, and then displays the result
         */
        private void checkAndDisplayMatrix() {
            if (checkMatrixBounds()) {
                setImageViewMatrix(getDrawMatrix());
            }
        }

        /**
         * Helper method that maps the supplied Matrix to the current Drawable
         *
         * @param matrix - Matrix to map Drawable against
         * @return RectF - Displayed Rectangle
         */
        private RectF getDisplayRect(Matrix matrix) {
            Drawable d = mImageView.getDrawable();
            if (d != null) {
                mDisplayRect.set(0, 0, d.getIntrinsicWidth(),
                        d.getIntrinsicHeight());
                matrix.mapRect(mDisplayRect);
                return mDisplayRect;
            }
            return null;
        }

        /**
         * Calculate Matrix for FIT_CENTER
         *
         * @param drawable - Drawable being displayed
         */
        private void updateBaseMatrix(Drawable drawable) {
            if (drawable == null) {
                return;
            }
            final float viewWidth = getImageViewWidth(mImageView);
            final float viewHeight = getImageViewHeight(mImageView);
            final int drawableWidth = drawable.getIntrinsicWidth();
            final int drawableHeight = drawable.getIntrinsicHeight();
            mBaseMatrix.reset();
            final float widthScale = viewWidth / drawableWidth;
            final float heightScale = viewHeight / drawableHeight;
            if (mScaleType == ScaleType.CENTER) {
                mBaseMatrix.postTranslate((viewWidth - drawableWidth) / 2F,
                        (viewHeight - drawableHeight) / 2F);

            } else if (mScaleType == ScaleType.CENTER_CROP) {
                float scale = Math.max(widthScale, heightScale);
                mBaseMatrix.postScale(scale, scale);
                mBaseMatrix.postTranslate((viewWidth - drawableWidth * scale) / 2F,
                        (viewHeight - drawableHeight * scale) / 2F);

            } else if (mScaleType == ScaleType.CENTER_INSIDE) {
                float scale = Math.min(1.0f, Math.min(widthScale, heightScale));
                mBaseMatrix.postScale(scale, scale);
                mBaseMatrix.postTranslate((viewWidth - drawableWidth * scale) / 2F,
                        (viewHeight - drawableHeight * scale) / 2F);

            } else {
                RectF mTempSrc = new RectF(0, 0, drawableWidth, drawableHeight);
                RectF mTempDst = new RectF(0, 0, viewWidth, viewHeight);
                if ((int) mBaseRotation % 180 != 0) {
                    mTempSrc = new RectF(0, 0, drawableHeight, drawableWidth);
                }
                switch (mScaleType) {
                    case FIT_CENTER:
                        mBaseMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.CENTER);
                        break;
                    case FIT_START:
                        mBaseMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.START);
                        break;
                    case FIT_END:
                        mBaseMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.END);
                        break;
                    case FIT_XY:
                        mBaseMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.FILL);
                        break;
                    default:
                        break;
                }
            }
            resetMatrix();
        }

        private boolean checkMatrixBounds() {
            final RectF rect = getDisplayRect(getDrawMatrix());
            if (rect == null) {
                return false;
            }
            final float height = rect.height(), width = rect.width();
            float deltaX = 0, deltaY = 0;
            final int viewHeight = getImageViewHeight(mImageView);
            if (height <= viewHeight) {
                switch (mScaleType) {
                    case FIT_START:
                        deltaY = -rect.top;
                        break;
                    case FIT_END:
                        deltaY = viewHeight - height - rect.top;
                        break;
                    default:
                        deltaY = (viewHeight - height) / 2 - rect.top;
                        break;
                }
                mVerticalScrollEdge = VERTICAL_EDGE_BOTH;
            } else if (rect.top > 0) {
                mVerticalScrollEdge = VERTICAL_EDGE_TOP;
                deltaY = -rect.top;
            } else if (rect.bottom < viewHeight) {
                mVerticalScrollEdge = VERTICAL_EDGE_BOTTOM;
                deltaY = viewHeight - rect.bottom;
            } else {
                mVerticalScrollEdge = VERTICAL_EDGE_NONE;
            }
            final int viewWidth = getImageViewWidth(mImageView);
            if (width <= viewWidth) {
                switch (mScaleType) {
                    case FIT_START:
                        deltaX = -rect.left;
                        break;
                    case FIT_END:
                        deltaX = viewWidth - width - rect.left;
                        break;
                    default:
                        deltaX = (viewWidth - width) / 2 - rect.left;
                        break;
                }
                mHorizontalScrollEdge = HORIZONTAL_EDGE_BOTH;
            } else if (rect.left > 0) {
                mHorizontalScrollEdge = HORIZONTAL_EDGE_LEFT;
                deltaX = -rect.left;
            } else if (rect.right < viewWidth) {
                deltaX = viewWidth - rect.right;
                mHorizontalScrollEdge = HORIZONTAL_EDGE_RIGHT;
            } else {
                mHorizontalScrollEdge = HORIZONTAL_EDGE_NONE;
            }
            // Finally actually translate the matrix
            mSuppMatrix.postTranslate(deltaX, deltaY);
            return true;
        }

        private int getImageViewWidth(ImageView imageView) {
            return imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
        }

        private int getImageViewHeight(ImageView imageView) {
            return imageView.getHeight() - imageView.getPaddingTop() - imageView.getPaddingBottom();
        }

        private void cancelFling() {
            if (mCurrentFlingRunnable != null) {
                mCurrentFlingRunnable.cancelFling();
                mCurrentFlingRunnable = null;
            }
        }

        private class AnimatedZoomRunnable implements Runnable {

            private final float mFocalX, mFocalY;
            private final long mStartTime;
            private final float mZoomStart, mZoomEnd;

            public AnimatedZoomRunnable(final float currentZoom, final float targetZoom,
                                        final float focalX, final float focalY) {
                mFocalX = focalX;
                mFocalY = focalY;
                mStartTime = System.currentTimeMillis();
                mZoomStart = currentZoom;
                mZoomEnd = targetZoom;
            }

            @Override
            public void run() {
                float t = interpolate();
                float scale = mZoomStart + t * (mZoomEnd - mZoomStart);
                float deltaScale = scale / getScale();
                onGestureListener.onScale(deltaScale, mFocalX, mFocalY);
                // We haven't hit our target scale yet, so post ourselves again
                if (t < 1f) {
                    mImageView.postOnAnimation(this);
                }
            }

            private float interpolate() {
                float t = 1f * (System.currentTimeMillis() - mStartTime) / mZoomDuration;
                t = Math.min(1f, t);
                t = mInterpolator.getInterpolation(t);
                return t;
            }
        }

        private class FlingRunnable implements Runnable {

            private final OverScroller mScroller;
            private int mCurrentX, mCurrentY;

            public FlingRunnable(Context context) {
                mScroller = new OverScroller(context);
            }

            public void cancelFling() {
                mScroller.forceFinished(true);
            }

            public void fling(int viewWidth, int viewHeight, int velocityX,
                              int velocityY) {
                final RectF rect = getDisplayRect();
                if (rect == null) {
                    return;
                }
                final int startX = Math.round(-rect.left);
                final int minX, maxX, minY, maxY;
                if (viewWidth < rect.width()) {
                    minX = 0;
                    maxX = Math.round(rect.width() - viewWidth);
                } else {
                    minX = maxX = startX;
                }
                final int startY = Math.round(-rect.top);
                if (viewHeight < rect.height()) {
                    minY = 0;
                    maxY = Math.round(rect.height() - viewHeight);
                } else {
                    minY = maxY = startY;
                }
                mCurrentX = startX;
                mCurrentY = startY;
                // If we actually can move, fling the scroller
                if (startX != maxX || startY != maxY) {
                    mScroller.fling(startX, startY, velocityX, velocityY, minX,
                            maxX, minY, maxY, 0, 0);
                }
            }

            @Override
            public void run() {
                if (mScroller.isFinished()) {
                    return; // remaining post that should not be handled
                }
                if (mScroller.computeScrollOffset()) {
                    final int newX = mScroller.getCurrX();
                    final int newY = mScroller.getCurrY();
                    mSuppMatrix.postTranslate(mCurrentX - newX, mCurrentY - newY);
                    checkAndDisplayMatrix();
                    mCurrentX = newX;
                    mCurrentY = newY;
                    // Post On animation
                    mImageView.postOnAnimation(this);
                }
            }
        }
    }

    public static class CustomGestureDetector {

        private static final int INVALID_POINTER_ID = -1;

        private int mActivePointerId = INVALID_POINTER_ID;
        private int mActivePointerIndex = 0;
        private final ScaleGestureDetector mDetector;

        private VelocityTracker mVelocityTracker;
        private boolean mIsDragging;
        private float mLastTouchX;
        private float mLastTouchY;
        private final float mTouchSlop;
        private final float mMinimumVelocity;
        private OnGestureListener mListener;

        CustomGestureDetector(Context context, OnGestureListener listener) {
            final ViewConfiguration configuration = ViewConfiguration
                    .get(context);
            mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
            mTouchSlop = configuration.getScaledTouchSlop();

            mListener = listener;
            ScaleGestureDetector.OnScaleGestureListener mScaleListener = new ScaleGestureDetector.OnScaleGestureListener() {

                @Override
                public boolean onScale(ScaleGestureDetector detector) {
                    float scaleFactor = detector.getScaleFactor();

                    if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor))
                        return false;

                    if (scaleFactor >= 0) {
                        mListener.onScale(scaleFactor,
                                detector.getFocusX(), detector.getFocusY());
                    }
                    return true;
                }

                @Override
                public boolean onScaleBegin(ScaleGestureDetector detector) {
                    return true;
                }

                @Override
                public void onScaleEnd(ScaleGestureDetector detector) {
                    // NO-OP
                }
            };
            mDetector = new ScaleGestureDetector(context, mScaleListener);
        }

        private float getActiveX(MotionEvent ev) {
            try {
                return ev.getX(mActivePointerIndex);
            } catch (Exception e) {
                return ev.getX();
            }
        }

        private float getActiveY(MotionEvent ev) {
            try {
                return ev.getY(mActivePointerIndex);
            } catch (Exception e) {
                return ev.getY();
            }
        }

        public boolean isScaling() {
            return mDetector.isInProgress();
        }

        public boolean isDragging() {
            return mIsDragging;
        }

        public boolean onTouchEvent(MotionEvent ev) {
            try {
                mDetector.onTouchEvent(ev);
                return processTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                // Fix for support lib bug, happening when onDestroy is called
                return true;
            }
        }

        private boolean processTouchEvent(MotionEvent ev) {
            final int action = ev.getAction();
            switch (action & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    mActivePointerId = ev.getPointerId(0);

                    mVelocityTracker = VelocityTracker.obtain();
                    if (null != mVelocityTracker) {
                        mVelocityTracker.addMovement(ev);
                    }

                    mLastTouchX = getActiveX(ev);
                    mLastTouchY = getActiveY(ev);
                    mIsDragging = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float x = getActiveX(ev);
                    final float y = getActiveY(ev);
                    final float dx = x - mLastTouchX, dy = y - mLastTouchY;

                    if (!mIsDragging) {
                        // Use Pythagoras to see if drag length is larger than
                        // touch slop
                        mIsDragging = Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
                    }

                    if (mIsDragging) {
                        mListener.onDrag(dx, dy);
                        mLastTouchX = x;
                        mLastTouchY = y;

                        if (null != mVelocityTracker) {
                            mVelocityTracker.addMovement(ev);
                        }
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    mActivePointerId = INVALID_POINTER_ID;
                    // Recycle Velocity Tracker
                    if (null != mVelocityTracker) {
                        mVelocityTracker.recycle();
                        mVelocityTracker = null;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mActivePointerId = INVALID_POINTER_ID;
                    if (mIsDragging) {
                        if (null != mVelocityTracker) {
                            mLastTouchX = getActiveX(ev);
                            mLastTouchY = getActiveY(ev);

                            // Compute velocity within the last 1000ms
                            mVelocityTracker.addMovement(ev);
                            mVelocityTracker.computeCurrentVelocity(1000);

                            final float vX = mVelocityTracker.getXVelocity(), vY = mVelocityTracker
                                    .getYVelocity();

                            // If the velocity is greater than minVelocity, call
                            // listener
                            if (Math.max(Math.abs(vX), Math.abs(vY)) >= mMinimumVelocity) {
                                mListener.onFling(mLastTouchX, mLastTouchY, -vX,
                                        -vY);
                            }
                        }
                    }

                    // Recycle Velocity Tracker
                    if (null != mVelocityTracker) {
                        mVelocityTracker.recycle();
                        mVelocityTracker = null;
                    }
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    final int pointerIndex = getPointerIndex(ev.getAction());
                    final int pointerId = ev.getPointerId(pointerIndex);
                    if (pointerId == mActivePointerId) {
                        // This was our active pointer going up. Choose a new
                        // active pointer and adjust accordingly.
                        final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                        mActivePointerId = ev.getPointerId(newPointerIndex);
                        mLastTouchX = ev.getX(newPointerIndex);
                        mLastTouchY = ev.getY(newPointerIndex);
                    }
                    break;
            }

            mActivePointerIndex = ev
                    .findPointerIndex(mActivePointerId != INVALID_POINTER_ID ? mActivePointerId
                            : 0);
            return true;
        }
    }

    public interface OnGestureListener {

        void onDrag(float dx, float dy);

        void onFling(float startX, float startY, float velocityX,
                     float velocityY);

        void onScale(float scaleFactor, float focusX, float focusY);

    }

    public interface OnMatrixChangedListener {

        /**
         * Callback for when the Matrix displaying the Drawable has changed. This could be because
         * the View's bounds have changed, or the user has zoomed.
         *
         * @param rect - Rectangle displaying the Drawable's new bounds.
         */
        void onMatrixChanged(RectF rect);
    }

    public interface OnOutsidePhotoTapListener {

        /**
         * The outside of the photo has been tapped
         */
        void onOutsidePhotoTap(ImageView imageView);
    }

    public interface OnPhotoTapListener {

        /**
         * A callback to receive where the user taps on a photo. You will only receive a callback if
         * the user taps on the actual photo, tapping on 'whitespace' will be ignored.
         *
         * @param view ImageView the user tapped.
         * @param x    where the user tapped from the of the Drawable, as percentage of the
         *             Drawable width.
         * @param y    where the user tapped from the top of the Drawable, as percentage of the
         *             Drawable height.
         */
        void onPhotoTap(ImageView view, float x, float y);
    }

    public interface OnScaleChangedListener {

        /**
         * Callback for when the scale changes
         *
         * @param scaleFactor the scale factor (less than 1 for zoom out, greater than 1 for zoom in)
         * @param focusX      focal point X position
         * @param focusY      focal point Y position
         */
        void onScaleChange(float scaleFactor, float focusX, float focusY);
    }

    public interface OnSingleFlingListener {

        /**
         * A callback to receive where the user flings on a ImageView. You will receive a callback if
         * the user flings anywhere on the view.
         *
         * @param e1        MotionEvent the user first touch.
         * @param e2        MotionEvent the user last touch.
         * @param velocityX distance of user's horizontal fling.
         * @param velocityY distance of user's vertical fling.
         */
        boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
    }

    public interface OnViewDragListener {

        /**
         * Callback for when the photo is experiencing a drag event. This cannot be invoked when the
         * user is scaling.
         *
         * @param dx The change of the coordinates in the x-direction
         * @param dy The change of the coordinates in the y-direction
         */
        void onDrag(float dx, float dy);
    }

    public interface OnViewTapListener {

        /**
         * A callback to receive where the user taps on a ImageView. You will receive a callback if
         * the user taps anywhere on the view, tapping on 'whitespace' will not be ignored.
         *
         * @param view - View the user tapped.
         * @param x    - where the user tapped from the left of the View.
         * @param y    - where the user tapped from the top of the View.
         */
        void onViewTap(View view, float x, float y);
    }

    static void checkZoomLevels(float minZoom, float midZoom,
                                float maxZoom) {
        if (minZoom >= midZoom) {
            throw new IllegalArgumentException(
                    "Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        } else if (midZoom >= maxZoom) {
            throw new IllegalArgumentException(
                    "Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }

    static boolean hasDrawable(ImageView imageView) {
        return imageView.getDrawable() != null;
    }

    static boolean isSupportedScaleType(final ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (scaleType) {
            case MATRIX:
                throw new IllegalStateException("Matrix scale type is not supported");
        }
        return true;
    }

    static int getPointerIndex(int action) {
        return (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
    }
}