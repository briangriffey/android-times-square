package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.CalendarRowView;

/**
 * class created by @briangriffey
 * http://www.briangriffey.com
 */
public class WeekView extends CalendarRowView {

    public WeekView(Context context, AttributeSet set, int cellStyle) {
        super(context, set);

        TypedArray a = context.obtainStyledAttributes(cellStyle, R.styleable.FillingTextView);
        float fillSize = a.getFloat(R.styleable.FillingTextView_percent, 0);
        int todayBackground = a.getResourceId(R.styleable.FillingTextView_todayBackgroundColor, 0);
        int highlight = a.getColor(R.styleable.FillingTextView_highlightColor, 0);

        a.recycle();

        for(int i = 0; i<7; i++) {
            CalendarCellView cellView = new CalendarCellView(context);
            cellView.setTextAppearance(context, cellStyle);

            if(fillSize != 0)
                cellView.setFillSize(fillSize);
            if(todayBackground != 0)
                cellView.setTodayBackgroundColor(todayBackground);
            if(highlight != 0)
                cellView.setTodayHighlightColor(highlight);

            addView(cellView);
        }
    }
}
