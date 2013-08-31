package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

/**
 * class created by @briangriffey
 * http://www.briangriffey.com
 */
public class FillingTextView extends TextView {


    public FillingTextView(Context context) {
        super(context);

    }

    public FillingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public FillingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int largestPossibleTextHeight = getMeasuredHeight() - getPaddingBottom() - getPaddingTop();
        final int actualTextHeight = largestPossibleTextHeight * 7 / 10;

        setTextSize(TypedValue.COMPLEX_UNIT_PX, actualTextHeight);
        setGravity(Gravity.CENTER);
    }
}
