package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.squareup.timessquare.CalendarRowView;
import com.squareup.timessquare.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * class created by @briangriffey
 * http://www.briangriffey.com
 */
public class WeekdayHeader extends FrameLayout {

    private CalendarRowView mHeaderViews;

    private DateFormat mWeekdayNameFormat;

    public WeekdayHeader(Context context, AttributeSet attrs, int gutterSize) {
        super(context, attrs);

        mWeekdayNameFormat = new SimpleDateFormat(context.getString(R.string.day_name_format));
        mHeaderViews = (CalendarRowView) LayoutInflater.from(context).inflate(R.layout.weekday_header, this, false);
        mHeaderViews.setGutterSize(gutterSize);
        addView(mHeaderViews);

        initializeHeaders();
    }

    private void initializeHeaders() {
        Calendar today = Calendar.getInstance();
        final int originalDayOfWeek = today.get(Calendar.DAY_OF_WEEK);

        int firstDayOfWeek = today.getFirstDayOfWeek();
        for (int offset = 0; offset < 7; offset++) {
            today.set(Calendar.DAY_OF_WEEK, firstDayOfWeek + offset);
            final TextView textView = (TextView) mHeaderViews.getChildAt(offset);
            textView.setText(mWeekdayNameFormat.format(today.getTime()));
        }
        today.set(Calendar.DAY_OF_WEEK, originalDayOfWeek);
    }


}
