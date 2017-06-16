package com.cysion.choseimagesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.cysion.choseimagesdemo.longimg.LongImageView;
import com.cysion.choseimagesdemo.photoview.PhotoView;
import com.cysion.choseimagesdemo.photoview.PhotoViewAttacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class ImagesActivity extends AppCompatActivity {

    private int mTest;
    private int mViewId;
    private MyViewPager mViewPager;
    private PhotoViewAttacher mMAttacher;
    private float totalY = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_acty);
        Intent intent = getIntent();
        mTest = intent.getIntExtra("test", 0);
        mViewId = intent.getIntExtra("view", 0);
        initView();
    }

    private void initView() {
        mViewPager = (MyViewPager) findViewById(R.id.vp_img_banner);
        initViewPager();
    }

    private void initViewPager() {
        final List<View> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PhotoView temp = new PhotoView(this);
            temp.setImageResource(ImgModel.imgSet[i]);
            if (temp.getDrawable().getIntrinsicHeight() < 2000) {
                data.add(temp);
            } else {
                LongImageView img = new LongImageView(this);
                img.setImage(ImgModel.imgSet[i]);
                data.add(img);
            }
        }
        final HomePagerAdapter adapter = new HomePagerAdapter(data);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mTest, false);
        mViewPager.setOnDownListener(new MyViewPager.OnDownListener() {
            @Override
            public void scrollDown(float y) {
                Log.e("flag--", "scrollDown(ImagesActivity.java:55)---->>" + y);
                totalY += y;
                mViewPager.scrollBy(0, -(int) y);
                if (y == 0) {
                    if (totalY > 800) {
                        finish();
                        return;
                    }
                    totalY = 0;
                    mViewPager.scrollTo(mViewPager.getScrollX(), 0);
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                View ppp = adapter.getPrimaryItem();
                if (ppp instanceof PhotoView) {
//                    ((PhotoView) ppp).zoomTo(1, 1, 1);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ImagesActivity.this, MainActivity.class);
        myIntent.putExtra("test2", mViewPager.getCurrentItem());
        myIntent.putExtra("view", mViewId);
        startActivity(myIntent);
        finish();
        this.overridePendingTransition(0, 0);
    }
}
