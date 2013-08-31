package com.briangriffey.horizontalcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.timessquare.R;

/**
 * class created by briangriffey
 */
public class MonthTitleView extends LinearLayout{

    private ImageView mLeftFlipper;
    private ImageView mRightFlipper;
    private TextView mTitleView;

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

    private void init(Context context) {
        mLeftFlipper = new ImageView(context);
        mRightFlipper = new ImageView(context);
        mTitleView = new TextView(context);

        //TODO: create a way to pass in custom images
        mLeftFlipper.setImageResource(R.drawable.left_flipper);
        mRightFlipper.setImageResource(R.drawable.right_flipper);

        //make the titleview span the majority of the space
        LayoutParams titleParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        //just have the images wrap content
        LayoutParams flipperParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        addView(mLeftFlipper, flipperParams);
        addView(mTitleView, titleParams);
        addView(mRightFlipper, flipperParams);
    }
}
