package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.timessquare.CalendarCellView;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by @briangriffey on 9/16/13.
 */
public class BiWeeklyView extends ViewGroup {

    private WeekView[] mWeekViews;
    private int mGutterSize;
    private Date mToday;

    public BiWeeklyView(Context context, AttributeSet attrs, int cellStyle) {
        super(context, attrs);
        init(context, attrs, cellStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;

        int totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        totalHeight -= mGutterSize * mWeekViews.length;
        totalHeight /= mWeekViews.length;

        int childMeasureSpec = MeasureSpec.makeMeasureSpec(totalHeight, MeasureSpec.EXACTLY);

        //add up all of the views from top to bottom
        for(WeekView weekView: mWeekViews) {
            weekView.measure(widthMeasureSpec, childMeasureSpec);
        }

       super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int height = getMeasuredHeight();
        int rowHeight = (getMeasuredHeight() - (mWeekViews.length * mGutterSize)) / mWeekViews.length;

        for(WeekView weekView: mWeekViews) {
            weekView.layout(0, t, r, t+rowHeight);
            t+= rowHeight;
            t+= mGutterSize;
        }
    }

    private void init(Context context, AttributeSet set, int cellStyle) {
        WeekView weekOne = new WeekView(context, set, cellStyle);
        WeekView weekTwo = new WeekView(context, set, cellStyle);

        mWeekViews = new WeekView[] {weekOne, weekTwo};

        addView(weekOne);
        addView(weekTwo);
    }

    public void setGutterSize(int size) {
        mGutterSize = size;
        for (WeekView weekView : mWeekViews) {
            weekView.setGutterSize(size);
        }
    }

    public void setStartDate(Calendar calendar) {

        for (WeekView weekView : mWeekViews) {
            for (int c = 0; c < 7; c++) {
//            MonthCellDescriptor cell = new MonthCellDescriptor();

                Date date = calendar.getTime();
                CalendarCellView cellView = (CalendarCellView) weekView.getChildAt(c);

                cellView.setText(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                cellView.setDate(date);
//                cellView.setOnClickListener(mClickListener);

                if (date.equals(mToday))
                    cellView.setSelected(true);
                else
                    cellView.setSelected(false);

                calendar.add(Calendar.DATE, 1);

//            cellView.setEnabled(cell.isCurrentMonth());
//
//            cellView.setSelectable(cell.isSelectable());
//            cellView.setSelected(cell.isSelected());
//              cellView.setCurrentMonth(cell.isCurrentMonth());
//            cellView.setToday(cell.isToday());
//            cellView.setRangeState(cell.getRangeState());
                cellView.setTag(date);
            }
        }
    }


    public void setToday(Date today) {
        this.mToday = today;
    }
}
