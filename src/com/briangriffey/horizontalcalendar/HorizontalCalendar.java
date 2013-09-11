package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.briangriffey.horizontalcalendar.R;

import java.util.Date;

/**
 * class created by briangriffey
 */
public class HorizontalCalendar extends LinearLayout implements View.OnClickListener {

    private MonthTitleView mTitleView;
    private ScrollingWeekView mWeekView;
    private Listener mListener;

    private View mSelectedView;

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
        int dividerSize = typedArray.getDimensionPixelSize(R.styleable.HorizontalCalendar_cellDividerSize, 0);

        setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams fullWidthParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mTitleView = new MonthTitleView(context, attrs);
        mTitleView.setDate(new Date());
        addView(mTitleView, fullWidthParams);

        WeekdayHeader header = new WeekdayHeader(context, attrs, dividerSize);
        addView(header, fullWidthParams);

        mWeekView = new ScrollingWeekView(context, dayStyle, dividerSize, this);
        addView(mWeekView, fullWidthParams);

        View rightFlipper = mTitleView.getRightFlipper();
        rightFlipper.setOnClickListener(this);

        View leftFlipper = mTitleView.getLeftFlipper();
        leftFlipper.setOnClickListener(this);

        typedArray.recycle();
    }


    @Override
    public void onClick(View v) {
        if (v == mTitleView.getLeftFlipper()) {
            mWeekView.scrollUpOneWeek();
        } else if (v == mTitleView.getRightFlipper()) {
            mWeekView.scrollDownOneWeek();
        } else {
            Object tag = v.getTag();
            if (tag == null) {
                return;
            } else {
                Date date = (Date) tag;
                setNewSelectedDate(v, date);
            }
        }

        setCorrectTitleIfNeeded();
    }

    private void setNewSelectedDate(View v, Date date) {

        mTitleView.setDate(date);
        mWeekView.setSelectedDate(date);
        if (v != mSelectedView) {
            if(mSelectedView != null)
                mSelectedView.setSelected(false);

            mSelectedView = v;
            mSelectedView.setSelected(true);
        }

        if (mListener != null) {
            mListener.onDatePicked(date);
        }
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    private void setCorrectTitleIfNeeded() {
        int visiblePosition = mWeekView.getFirstVisiblePosition();
    }

    public static interface Listener {
        void onDatePicked(Date date);
    }
}
