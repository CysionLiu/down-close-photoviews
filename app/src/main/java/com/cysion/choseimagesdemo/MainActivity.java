package com.cysion.choseimagesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPager mViewPager2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_home_banner);
        mViewPager2 = (ViewPager) findViewById(R.id.vp_home_banner2);
        initViewPager1();
        initViewPager2();
    }

    private void initViewPager1() {
        List<ImageView> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(this);
            final int finalI = i;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, ImagesActivity.class);
                    myIntent.putExtra("test", finalI);
                    myIntent.putExtra("view", 0);
                    startActivity(myIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                            mViewPager, "viewpager").toBundle()
                    );
                }
            });
            img.setImageResource(ImgModel.imgSet[i]);
            data.add(img);
        }
        HomePagerAdapter adapter = new HomePagerAdapter(data);
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(false, null);
    }

    private void initViewPager2() {
        List<ImageView> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(this);
            final int finalI = i;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, ListViewActivty.class);
                    myIntent.putExtra("test", finalI);
                    myIntent.putExtra("view", 1);
                    startActivity(myIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                            mViewPager2, "list").toBundle()
                    );
                }
            });
            img.setImageResource(ImgModel.imgSet[i]);
            data.add(img);
        }
        HomePagerAdapter adapter = new HomePagerAdapter(data);
        mViewPager2.setAdapter(adapter);
        mViewPager2.setPageTransformer(false, null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int test = intent.getIntExtra("test2", 0);
        int view = intent.getIntExtra("view", 0);
        if (view == 0) {
            mViewPager.setCurrentItem(test, false);
        } else {
            mViewPager2.setCurrentItem(test, false);

        }
    }

    public void changePageWithoutAnim(View view) {
        mViewPager.setCurrentItem(3,false);
    }
}
