package com.briangriffey.horizontalcalendar;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * class created by briangriffey
 */
public class ScrollingWeekView extends ListView {

    //TODO: make this styleable
    private static final int ROWS_TO_DISPLAY = 3;
    private static final int ANIMATION_TIME = 100;
    private int mRowHeight;

    public ScrollingWeekView(Context context) {
        super(context);
        setAdapter(new WeekAdapter(context));

        setDivider(null);
        setDividerHeight(0);


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

    public void scrollDownOneWeek() {
        smoothScrollBy(mRowHeight, ANIMATION_TIME);
    }

    public void scrollUpOneWeek() {
       smoothScrollBy(-mRowHeight, ANIMATION_TIME);
    }
}
