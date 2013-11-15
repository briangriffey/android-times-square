package com.briangriffey.horizontalcalendar;

import android.content.res.ColorStateList;
import android.content.res.Resources;
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

    private static final int HAS_EVENTS_CIRCLE_RADIUS = 32;

    private Paint mPaint = new Paint();
    private int mBackgroundColor;
    private int mHighlightColor;
    private Resources mResources;
    private ColorStateList mColorStateList;

    public CellBackgroundDrawable(Resources resources) {
        mResources = resources;

    }

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
        mColorStateList = mResources.getColorStateList(color);
    }

    public void setHighlightColor(int color) {
        mHighlightColor = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();

        int color = mColorStateList.getColorForState(getState(), 0);

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        canvas.drawRect(bounds, mPaint);

        int[] state = getState();
        for (int i = 0; i < state.length; i++) {
            if (state[i] == R.attr.state_today) {
                mPaint.setColor(mHighlightColor);
                canvas.drawCircle(bounds.centerX(), bounds.centerY(), bounds.width()/2 - 4, mPaint);
            } else if(state[i] == R.attr.state_has_items) {
                int height = bounds.height();
                int radius = height / 20;

                mPaint.setColor(mHighlightColor);
                int circleMiddle = height * 13/16;

                canvas.drawCircle(bounds.centerX(), circleMiddle, radius, mPaint);
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
