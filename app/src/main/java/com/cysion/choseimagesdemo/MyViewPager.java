package com.cysion.choseimagesdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.cysion.choseimagesdemo.longimg.LongImageView;

/**
 * Created by Administrator on 2017/6/14.
 */

public class MyViewPager extends ViewPager {

    private float downY = 0;
    private float lastY = 0;
    private OnDownListener mOnDownListener;

    public void setOnDownListener(OnDownListener aOnDownListener) {
        mOnDownListener = aOnDownListener;
    }

    public static interface OnDownListener{
        void scrollDown(float y);
    }

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ev.getY()-downY>100) {
                    mOnDownListener.scrollDown(ev.getY()-lastY);
                    lastY  = ev.getY();
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                mOnDownListener.scrollDown(0);
                break;
            default:

                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                downY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e("flag--","onTouchEvent(MyViewPager.java:41)---->>"+downY);
//                Log.e("flag--","onTouchEvent(MyViewPager.java:43)---->>"+ev.getY());
//                if (ev.getY()-downY>100) {
//                    mOnDownListener.scrollDown();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//            default:
//
//                break;
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                lastY  = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                View ppp= ((HomePagerAdapter)getAdapter()).getPrimaryItem();
                float top = ((LongImageView) ppp).getTargetRect().top;
                Log.e("flag--","onInterceptTouchEvent(MyViewPager.java:97)---->>"+top);
                if (ev.getY()-downY>100&&top<10) {
                    mOnDownListener.scrollDown(ev.getY()-lastY);
                    lastY  = ev.getY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
