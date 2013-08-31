package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.timessquare.R;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * class created by briangriffey
 */
public class MonthTitleView extends ViewGroup{

    private static final int TITLE_CELL_SPAN = 5;
    private static final int DAYS_IN_A_WEEK = 7;

    private ImageView mLeftFlipper;
    private ImageView mRightFlipper;
    private TextView mTitleView;

    private int mCellSize;

    public MonthTitleView(Context context) {
        super(context);
        init(context);
    }

    public MonthTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MonthTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        mCellSize = totalWidth / DAYS_IN_A_WEEK;

        int flipperWidthSpec = makeMeasureSpec(mCellSize, EXACTLY);
        int flipperHeightSpec = flipperWidthSpec;

        mLeftFlipper.measure(flipperWidthSpec, flipperHeightSpec);
        mRightFlipper.measure(flipperWidthSpec, flipperHeightSpec);

        int rowHeight = 0;
        if (mLeftFlipper.getMeasuredHeight() > rowHeight) {
            rowHeight = mLeftFlipper.getMeasuredHeight();
        }

        int titleWithSpec = makeMeasureSpec(mCellSize * TITLE_CELL_SPAN, EXACTLY);
        mTitleView.measure(titleWithSpec, flipperHeightSpec);

        final int widthWithPadding = totalWidth + getPaddingLeft() + getPaddingRight();
        final int heightWithPadding = rowHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(widthWithPadding, heightWithPadding);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int cellHeight = bottom - top;

        mLeftFlipper.layout(0, 0, mCellSize, cellHeight);
        mTitleView.layout(mCellSize, 0, mTitleView.getMeasuredWidth(), cellHeight);
        mRightFlipper.layout(mCellSize * (DAYS_IN_A_WEEK - 1), 0, mCellSize * DAYS_IN_A_WEEK, cellHeight);

    }

    private void init(Context context) {
        mLeftFlipper = new ImageView(context);
        mRightFlipper = new ImageView(context);
        mTitleView = new TextView(context);

        mTitleView.setText("Test Text");
        mTitleView.setGravity(Gravity.CENTER);

        mLeftFlipper.setScaleType(ImageView.ScaleType.CENTER);
        mRightFlipper.setScaleType(ImageView.ScaleType.CENTER);

        //TODO: create a way to pass in custom images
        mLeftFlipper.setImageResource(R.drawable.left_flipper);
        mRightFlipper.setImageResource(R.drawable.right_flipper);

        super.addView(mLeftFlipper);
        super.addView(mTitleView);
        super.addView(mRightFlipper);
    }

    @Override
    public void addView(View child) {
        //no adding views for you!
    }

    public View getRightFlipper() {
        return mRightFlipper;
    }

    public View getLeftFlipper() {
        return mLeftFlipper;
    }
}
