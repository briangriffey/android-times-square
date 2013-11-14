// Copyright 2013 Square, Inc.

package com.squareup.timessquare;

import android.content.Context;
import android.util.AttributeSet;

import com.briangriffey.horizontalcalendar.CellBackgroundDrawable;
import com.briangriffey.horizontalcalendar.FillingTextView;
import com.briangriffey.horizontalcalendar.R;
import com.squareup.timessquare.MonthCellDescriptor.RangeState;

import java.util.Date;

public class CalendarCellView extends FillingTextView {

    private static final int[] STATE_SELECTABLE = {
            R.attr.state_selectable
    };
    private static final int[] STATE_CURRENT_MONTH = {
            R.attr.state_current_month
    };
    private static final int[] STATE_TODAY = {
            R.attr.state_today
    };
    private static final int[] STATE_RANGE_FIRST = {
            R.attr.state_range_first
    };
    private static final int[] STATE_RANGE_MIDDLE = {
            R.attr.state_range_middle
    };
    private static final int[] STATE_RANGE_LAST = {
            R.attr.state_range_last
    };

    private boolean isSelectable = false;
    private boolean isCurrentMonth = false;
    private boolean isToday = false;
    private RangeState rangeState = RangeState.NONE;
    private Date date;
    private CellBackgroundDrawable mBackground;

    public CalendarCellView(Context context) {
        super(context);
        mBackground = new CellBackgroundDrawable(context.getResources());
        setBackgroundDrawable(mBackground);

    }

    public CalendarCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBackground = new CellBackgroundDrawable(context.getResources());
        setBackgroundDrawable(mBackground);

    }

    public CalendarCellView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mBackground = new CellBackgroundDrawable(context.getResources());
        setBackgroundDrawable(mBackground);

    }

    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
        refreshDrawableState();
    }

    public void setCurrentMonth(boolean isCurrentMonth) {
        this.isCurrentMonth = isCurrentMonth;
        refreshDrawableState();
    }

    public void setToday(boolean isToday) {
        this.isToday = isToday;
        refreshDrawableState();
    }

    public void setRangeState(MonthCellDescriptor.RangeState rangeState) {
        this.rangeState = rangeState;
        refreshDrawableState();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 4);

        if (isSelectable) {
            mergeDrawableStates(drawableState, STATE_SELECTABLE);
        }

        if (isCurrentMonth) {
            mergeDrawableStates(drawableState, STATE_CURRENT_MONTH);
        }

        if (isToday) {
            mergeDrawableStates(drawableState, STATE_TODAY);
        }

        if (rangeState == MonthCellDescriptor.RangeState.FIRST) {
            mergeDrawableStates(drawableState, STATE_RANGE_FIRST);
        } else if (rangeState == MonthCellDescriptor.RangeState.MIDDLE) {
            mergeDrawableStates(drawableState, STATE_RANGE_MIDDLE);
        } else if (rangeState == RangeState.LAST) {
            mergeDrawableStates(drawableState, STATE_RANGE_LAST);
        }

        if(mBackground != null)
            mBackground.setState(drawableState);

        return drawableState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTodayHighlightColor(int color) {
        mBackground.setHighlightColor(color);
    }

    public void setTodayBackgroundColor(int color) {
        mBackground.setBackgroundColor(color);
    }

}
