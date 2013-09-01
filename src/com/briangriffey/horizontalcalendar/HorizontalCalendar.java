package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.squareup.timessquare.R;

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

    private void init(Context context, AttributeSet attrs) {
        //pull out custom colors and such
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalCalendar, 0, R.style.DefaultCalendarStyle);
        int dayStyle = typedArray.getResourceId(R.styleable.HorizontalCalendar_dayStyle, R.style.CalendarCell_CalendarDate);

        setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams fullWidthParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mTitleView = new MonthTitleView(context, attrs);
        addView(mTitleView, fullWidthParams);

        WeekdayHeader header = new WeekdayHeader(context, attrs);
        addView(header, fullWidthParams);

        mWeekView = new ScrollingWeekView(context, dayStyle);
        addView(mWeekView, fullWidthParams);

        View rightFlipper = mTitleView.getRightFlipper();
        rightFlipper.setOnClickListener(this);

        View leftFlipper = mTitleView.getLeftFlipper();
        leftFlipper.setOnClickListener(this);

        typedArray.recycle();

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
