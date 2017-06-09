package com.guanshaoye.glglteacher.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by karl on 2017/5/26.
 */

public class MyGridView extends GridView {
    public MyGridView(Context context) {

        super(context);

    }

    public MyGridView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public MyGridView(Context context, AttributeSet attrs, int  defStyle) {

        super(context, attrs, defStyle);

    }
//    @Override

    //    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//
//            return true;  //禁止GridView滑动
//
//        }
//
//        return super.dispatchTouchEvent(ev);
//
//    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
