package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.R;

/**
 * class created by briangriffey
 */
public class WeekAdapter extends BaseAdapter {

    private static final int WEEKS_IN_YEAR = 52;
    private final LayoutInflater mInflater;

    public WeekAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = mInflater.inflate(R.layout.week, parent, false);

        ViewGroup groupView = (ViewGroup)convertView;

        for (int c = 0; c < 7; c++) {
//            MonthCellDescriptor cell = week.get(c);
            CalendarCellView cellView = (CalendarCellView) groupView.getChildAt(c);

            cellView.setText(Integer.toString(c));
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
