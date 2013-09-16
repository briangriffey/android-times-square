package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.MonthCellDescriptor;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class created by briangriffey
 */
public class WeekAdapter extends PagerAdapter {

    private static final int WEEKS_IN_YEAR = 52;

    private final Context mContext;
    private final int mCellStyle;
    private final int mDividerSize;
    private final View.OnClickListener mClickListener;

    private Calendar mCalendar;
    private Date mToday;

    private Date mSelectedDate;

    private BlockingQueue<BiWeeklyView> mRecycleViews;

    public WeekAdapter(Context context, int cellStyle, int dividerSize, View.OnClickListener onClickListener) {
        mContext = context;
        mCalendar = Calendar.getInstance();

        mToday = new Date();
        mCellStyle = cellStyle;
        mDividerSize = dividerSize;

        mClickListener = onClickListener;

        mRecycleViews = new LinkedBlockingQueue<BiWeeklyView>();
    }

    @Override
    public int getCount() {
        return 52;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public BiWeeklyView instantiateItem(ViewGroup container, int position) {
        BiWeeklyView recycledView = mRecycleViews.poll();
        recycledView = getView(position, recycledView, container);
        container.addView(recycledView);

        return recycledView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        BiWeeklyView weekView = (BiWeeklyView) object;
        container.removeView(weekView);
        //put this back into the list of things that we can get
        mRecycleViews.offer(weekView);
    }


    public BiWeeklyView getView(int weeksPastToday, BiWeeklyView convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = new BiWeeklyView(mContext, null, mCellStyle);
            convertView.setGutterSize(mDividerSize);
        }

        mCalendar.setTime(mToday);
        mCalendar.add(Calendar.DATE, 7 * weeksPastToday);

        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        int firstDayOfWeek = mCalendar.getFirstDayOfWeek();

        int difference = firstDayOfWeek - dayOfWeek;
        mCalendar.add(Calendar.DATE, difference);

        convertView.setStartDate(mCalendar);

        return convertView;
    }

    public void setSelectedDate(Date date) {
        mSelectedDate = date;
    }


}
