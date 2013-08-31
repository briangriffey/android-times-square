package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * class created by briangriffey
 */
public class HorizontalCalendar extends LinearLayout implements View.OnClickListener{

    private MonthTitleView mTitleView;
    private ScrollingWeekView mWeekView;

    public HorizontalCalendar(Context context) {
        super(context);
        init(context, null);
    }

    public HorizontalCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HorizontalCalendar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams fullWidthParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mTitleView = new MonthTitleView(context);
        addView(mTitleView, fullWidthParams);

        WeekdayHeader header = new WeekdayHeader(context, attrs);
        addView(header, fullWidthParams);

        mWeekView = new ScrollingWeekView(context);
        addView(mWeekView, fullWidthParams);

        View rightFlipper = mTitleView.getRightFlipper();
        rightFlipper.setOnClickListener(this);

        View leftFlipper = mTitleView.getLeftFlipper();
        leftFlipper.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == mTitleView.getLeftFlipper()) {
            mWeekView.scrollUpOneWeek();
        } else if(v == mTitleView.getRightFlipper()) {
            mWeekView.scrollDownOneWeek();
        }
    }
}
