package com.joker.demo.viewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.joker.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends AppCompatActivity implements View.OnClickListener{
    String TAG="ViewPager2Activity";
    RadioButton mRbH,mRbV;
    ViewPager2 mPager2;
    Switch mSwitch;
    Button mBtnSimulateSwipe,mBtnPageId;
    EditText mEtPageId;
    SeekBar mSbSpace;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);

        initView();
        List<String> colors = new ArrayList<>();
        colors.add("#FFFF00");
        colors.add("#FF0000");
        colors.add("#AAFF00");
        colors.add("#FF44FF");
        colors.add("#EEFFEE");
        colors.add("#EEE000");


        Vp2Adapter adapter = new Vp2Adapter(colors);
        mPager2.setAdapter(adapter);

        //一屏多页
        mPager2.setOffscreenPageLimit(2);
        RecyclerView recyclerView = (RecyclerView) mPager2.getChildAt(0);
        int padding = 200;
        recyclerView.setPadding(padding, 0, padding, 0);
        recyclerView.setClipToPadding(false);


        mSbSpace.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPager2.setPageTransformer(new MarginPageTransformer(50*progress/100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //禁止滑动
                    mPager2.setUserInputEnabled(false);
                }else{
                    mPager2.setUserInputEnabled(true);
                }
            }
        });

        mBtnSimulateSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mPager2.isFakeDragging()){
                    mPager2.beginFakeDrag();
                    if (mPager2.fakeDragBy(-100)) {
                        if(mPager2.isFakeDragging()){
                            mPager2.endFakeDrag();
                        }
                    }
                }
            }
        });

        mPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //当滚动当前页面时，将调用此方法
                Log.e(TAG,"onPageScrolled===position::"+position+"::positionOffset::"+positionOffset+"::positionOffsetPixels::"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.e(TAG,"onPageSelected===position::"+position);
                //当选择新页面时，将调用此方法。动画不是一定完成。
                try {
                    Log.e(TAG,"count::"+mPager2.getChildCount()+"");
                    Log.e(TAG,"id::"+mPager2.getChildAt(position).getId()+"");
                }catch (Exception e){}

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                Log.e(TAG,"onPageScrollStateChanged===state::"+state);
                //当滚动状态改变时调用。
            }
        });

    }

    private void initView() {
        mPager2 = findViewById(R.id.vp2);
        mRbH=findViewById(R.id.rb_h);
        mRbV=findViewById(R.id.rb_v);
        mSwitch=findViewById(R.id.switch_swipe);
        mBtnSimulateSwipe=findViewById(R.id.btn_simulate_swipe);
        mEtPageId=findViewById(R.id.et_pageid);
        mBtnPageId=findViewById(R.id.btn_pageid);
        mSbSpace=findViewById(R.id.sb_space);

        mRbH.setOnClickListener(this);
        mRbV.setOnClickListener(this);
        mBtnSimulateSwipe.setOnClickListener(this);
        mBtnPageId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_h:
                mPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                break;

            case R.id.rb_v:
                mPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                break;

            case R.id.btn_pageid:
                int pageId= Integer.parseInt(mEtPageId.getText().toString());
                mPager2.setCurrentItem(pageId);
                break;
        }

    }
}
