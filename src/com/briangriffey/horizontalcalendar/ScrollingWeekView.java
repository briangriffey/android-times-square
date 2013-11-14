package com.briangriffey.horizontalcalendar;


import android.content.Context;
import android.support.v4.view.ViewPager;

import java.util.Date;
import java.util.Set;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * class created by briangriffey
 */
public class ScrollingWeekView extends ViewPager {

    private static final int ROWS_TO_DISPLAY = 2;
    private int mRowHeight;

    private WeekAdapter mAdapter;

    public ScrollingWeekView(Context context, int cellStyle, int dividerSize, OnClickListener clickListener) {
        super(context);

        mAdapter = new WeekAdapter(context, cellStyle, dividerSize, clickListener);
        setAdapter(mAdapter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //get the width
        final int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        //figure out how big each cell will be
        int cellSize = totalWidth / 7;
        int cellWidthSpec = makeMeasureSpec(cellSize, EXACTLY);

        //since the cells are square then make the height the width
        mRowHeight = cellSize;
        //figure out how many rows to display
        int totalHeight = mRowHeight * ROWS_TO_DISPLAY;
        //set the heightmeasure spect to be this length
        heightMeasureSpec = makeMeasureSpec(totalHeight, EXACTLY);

        //call the super so that the listview sets the correct height and figures how how many recycle views it needs etc
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void scrollBack() {
       if(getCurrentItem() - 1 >= 0) {
           setCurrentItem(getCurrentItem() - 1, true);
       }
    }

    public void scrollForward() {
       if(getCurrentItem() + 1 < mAdapter.getCount())
           setCurrentItem(getCurrentItem() + 1, true);
    }

    public void setSelectedDate(Date date) {
       mAdapter.setSelectedDate(date);
    }

    public Date getDateForPosition(int i){
        return mAdapter.getDateForPosition(i);
    }

    public void setDates(Set<Date> dateSet) {
        mAdapter.setDates(dateSet);
    }
}
