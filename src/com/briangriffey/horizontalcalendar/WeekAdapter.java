package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.R;

import java.util.Calendar;
import java.util.Date;

/**
 * class created by briangriffey
 */
public class WeekAdapter extends BaseAdapter {

    private static final int WEEKS_IN_YEAR = 52;
    private final Context mContext;
    private final int mCellStyle;
    private final int mDividerSize;

    private Calendar mCalendar;
    private Date mToday;

    public WeekAdapter(Context context, int cellStyle, int dividerSize) {
        mContext = context;
        mCalendar = Calendar.getInstance();

        mToday = new Date();
        mCellStyle = cellStyle;
        mDividerSize = dividerSize;
    }

    @Override
    public int getCount() {
        return 52;
    }

    @Override
    public Object getItem(int position) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int position) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int weeksPastToday, View convertView, ViewGroup parent) {

        if (convertView == null) {
            WeekView weekView = new WeekView(mContext, null, mCellStyle);
            weekView.setGutterSize(mDividerSize);
            convertView = weekView;
        }

        mCalendar.setTime(mToday);
        mCalendar.add(Calendar.DATE, 7 * weeksPastToday);

        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        int firstDayOfWeek = mCalendar.getFirstDayOfWeek();

        int difference = firstDayOfWeek - dayOfWeek;
        mCalendar.add(Calendar.DATE, difference);

        ViewGroup groupView = (ViewGroup) convertView;

        for (int c = 0; c < 7; c++) {
//            MonthCellDescriptor cell = week.get(c);
            CalendarCellView cellView = (CalendarCellView) groupView.getChildAt(c);

            cellView.setText(Integer.toString(mCalendar.get(Calendar.DAY_OF_MONTH)));
            mCalendar.add(Calendar.DATE, 1);
//            cellView.setEnabled(cell.isCurrentMonth());
//
//            cellView.setSelectable(cell.isSelectable());
//            cellView.setSelected(cell.isSelected());
//            cellView.setCurrentMonth(cell.isCurrentMonth());
//            cellView.setToday(cell.isToday());
//            cellView.setRangeState(cell.getRangeState());
//            cellView.setTag(cell);
        }


        return convertView;
    }
}
