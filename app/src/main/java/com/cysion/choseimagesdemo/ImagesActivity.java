package com.cysion.choseimagesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.cysion.choseimagesdemo.longimg.LongImageView;
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
        final List<LongImageView> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            LongImageView img = new LongImageView(this);
            img.setImage(ImgModel.imgSet[i]);
            data.add(img);
        }
        HomePagerAdapter adapter = new HomePagerAdapter(data);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mTest, false);
        mViewPager.setOnDownListener(new MyViewPager.OnDownListener() {
            @Override
            public void scrollDown(float y) {
                Log.e("flag--","scrollDown(ImagesActivity.java:55)---->>"+y);
                mViewPager.scrollBy(0, -(int) y);
                if (y==0) {
                    mViewPager.scrollTo(mViewPager.getScrollX(),0);
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
