package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * class created by briangriffey
 */
public class HorizontalCalendar extends LinearLayout {

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

        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        WeekdayHeader header = new WeekdayHeader(context, attrs);
        header.setLayoutParams(params);

        addView(header);
    }
}
