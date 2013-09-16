package com.briangriffey.horizontalcalendar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * class created by @briangriffey
 * http://www.briangriffey.com
 */
public class CellBackgroundDrawable extends Drawable {

    private Paint mPaint = new Paint();
    private int mBackgroundColor;
    private int mHighlightColor;

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
    }

    public void setHighlightColor(int color) {
        mHighlightColor = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mBackgroundColor);
        canvas.drawRect(bounds, mPaint);

        int[] state = getState();
        for (int i = 0; i < state.length; i++) {
            if (state[i] == R.attr.state_today) {
                mPaint.setColor(mHighlightColor);
                canvas.drawCircle(bounds.centerX(), bounds.centerY(), bounds.width()/2 - 4, mPaint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getOpacity() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
