package com.cysion.choseimagesdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.cysion.choseimagesdemo.longimg.LongImageView;
import com.cysion.choseimagesdemo.photoview.PhotoView;

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

    public static interface OnDownListener {
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
                if (ev.getY() - downY > 100) {
                    mOnDownListener.scrollDown(ev.getY() - lastY);
                    lastY = ev.getY();
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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float top = 0;
                float left = 0;
                float right = 0;
                if (ev.getPointerCount() > 1) {
                    return false;
                }
                View ppp = ((HomePagerAdapter) getAdapter()).getPrimaryItem();
                if (ppp instanceof PhotoView) {
                    top = ((PhotoView) ppp).getDisplayRect().top;
                    left = ((PhotoView) ppp).getDisplayRect().left;
                    right = ((PhotoView) ppp).getDisplayRect().right;
                    Log.e("flag--", "onInterceptTouchEvent(MyViewPager.java:84)-->>" + left);
                    Log.e("flag--", "onInterceptTouchEvent(MyViewPager.java:85)-->>" + right);
                    Log.e("flag--", "onInterceptTouchEvent(MyViewPager.java:86)-->>" + ((PhotoView) ppp).getScale());
                    if (right > getResources().getDisplayMetrics().widthPixels + 50 && ev.getY() - downY < 200
                            && ((PhotoView) ppp).getScale() > 1.1f && left < 0) {
                        Log.e("flag--", "onInterceptTouchEvent(MyViewPager.java:89)-->>");
                        return false;
                    }
                    if (ev.getY() - downY > 200 && top == 0) {
                        mOnDownListener.scrollDown(ev.getY() - lastY);
                        lastY = ev.getY();
                        return true;
                    }
                } else {
                    top = ((LongImageView) ppp).getTargetRect().top;
                    if (ev.getY() - downY > 200 && top < 30) {
                        mOnDownListener.scrollDown(ev.getY() - lastY);
                        lastY = ev.getY();
                        return true;
                    }
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
