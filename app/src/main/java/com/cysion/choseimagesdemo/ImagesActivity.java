package com.cysion.choseimagesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class ImagesActivity extends AppCompatActivity {

    private int mTest;
    private int mViewId;
    private ViewPager mViewPager;

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
        mViewPager = (ViewPager) findViewById(R.id.vp_img_banner);
        initViewPager();
    }

    private void initViewPager() {
        List<ImageView> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(this);
            img.setImageResource(ImgModel.imgSet[i]);
            data.add(img);
        }
        HomePagerAdapter adapter = new HomePagerAdapter(data);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mTest,false);
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
