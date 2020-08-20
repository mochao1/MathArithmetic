package com.demo.amt.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Main8Activity extends AppCompatActivity {
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    int[] imgRes = {R.mipmap.abc, R.mipmap.acb, R.mipmap.cba};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        //设置Page间间距
        mViewPager.setPageMargin(20);
        //设置缓存的页面数量
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer(true, new RotateDownPageTransformer(false));
        mViewPager.setAdapter(mAdapter = new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView view = new ImageView(Main8Activity.this);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setImageResource(imgRes[position]);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });

//        permission();
    }

    private void permission() {
        int scaledTouchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        Log.d("TAG","scaledTouchSlop:"+scaledTouchSlop);
        Intent intent=getIntent();
        Log.d("TAG","data"+intent.getStringExtra("mc"));
        finish();
    }
}
