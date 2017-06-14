package com.cysion.choseimagesdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class HomePagerAdapter extends PagerAdapter {

    List<? extends View> imgs;

    public HomePagerAdapter(List<? extends View> data) {
        imgs = data;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        Log.e("flag--","HomePagerAdapter--instantiateItem--35--");
//        View view = imgs.get(position%imgs.size());
//        if (view.getParent() != null) {
//            ((ViewGroup) (view.getParent())).removeView(view);
//        }
        container.addView(imgs.get(position));
        return imgs.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs.get(position));
    }
    private View mCurrentView;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View)object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }
}
