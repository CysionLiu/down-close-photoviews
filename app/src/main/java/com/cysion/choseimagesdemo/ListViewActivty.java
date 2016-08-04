package com.cysion.choseimagesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xianshang.liu on 2016/8/4.
 */
public class ListViewActivty extends AppCompatActivity {

    private int mTest;
    private int mViewId;
    private ListView mListView;
    private List<Integer> mData;
    private int curItemId = 0;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_acty);
        Intent intent = getIntent();
        mTest = intent.getIntExtra("test", 0);
        mViewId = intent.getIntExtra("view", 0);
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_img_banner);
        initList();
    }

    private void initList() {
        mData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mData.add(ImgModel.imgSet[i]);
        }
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
        mListView.setSelection(mTest);
        watchScroll();
    }

    private void watchScroll() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                curItemId = firstVisibleItem;
                Log.e("flag--","ListViewActivty--onScroll--66--"+curItemId);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("test2", curItemId);
        myIntent.putExtra("view", mViewId);
        startActivity(myIntent);
        finish();
        this.overridePendingTransition(0, 0);
    }

    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.img_item, parent, false);
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.img_item);
            img.setImageResource(mData.get(position));
            return convertView;
        }
    }
}
