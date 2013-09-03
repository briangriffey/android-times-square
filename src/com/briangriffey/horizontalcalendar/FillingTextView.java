package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import com.squareup.timessquare.R;

/**
 * class created by @briangriffey
 * http://www.briangriffey.com
 */
public class FillingTextView extends TextView {

    private static final float DEFAULT_FILL_SIZE = .7f;

    private float mFillSize;

    public FillingTextView(Context context) {
        super(context);
        mFillSize = DEFAULT_FILL_SIZE;
    }

    public FillingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FillingTextView);
        mFillSize = a.getFloat(R.styleable.FillingTextView_percent, DEFAULT_FILL_SIZE);
        a.recycle();

    }

    public FillingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int largestPossibleTextHeight = getMeasuredHeight() - getPaddingBottom() - getPaddingTop();
        final int actualTextHeight = (int)(largestPossibleTextHeight * mFillSize);

        setTextSize(TypedValue.COMPLEX_UNIT_PX, actualTextHeight);
        setGravity(Gravity.CENTER);
    }

    public float getFillSize() {
        return mFillSize;
    }

    public void setFillSize(float fillSize) {
        this.mFillSize = fillSize;
        requestLayout();
    }
}