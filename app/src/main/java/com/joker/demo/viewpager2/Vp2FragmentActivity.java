package com.joker.demo.viewpager2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.joker.demo.R;

import java.util.ArrayList;
import java.util.List;

public class Vp2FragmentActivity extends AppCompatActivity implements Vp2Fragment.OnFragmentInteractionListener {
    AdapterFragmentPager pagerAdapter;
    ViewPager2 pager2;
    Button mBtnVpCommon;

    Handler handler = new Handler();
    private String TAG = "Vp2FragmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp2_fragment);

        actionbarStyle();

        mBtnVpCommon=findViewById(R.id.btn_vp_common);
        mBtnVpCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Vp2FragmentActivity.this,ViewPager2Activity.class);
                startActivity(intent);
            }
        });

        Log.e("xiang", getSupportFragmentManager().getFragments().size() + "");
        try {
            Log.e("xiang", getSupportFragmentManager().getFragments().get(0).getTag());
        } catch (Exception e) {

        }
        pager2 = findViewById(R.id.vp2);
        final Vp2Fragment fragment1 = new Vp2Fragment();
        Fragment fragment2 = new Vp2Fragment();
        Fragment fragment3 = new Vp2Fragment();
        Fragment fragment4 = new Vp2Fragment();


        List<Fragment> fragmengts = new ArrayList<>();
        fragmengts.add(fragment1);
        fragmengts.add(fragment2);
        fragmengts.add(fragment3);
        fragmengts.add(fragment4);


        pager2.setOffscreenPageLimit(2);
        pagerAdapter = new AdapterFragmentPager(this, fragmengts);

        pager2.setAdapter(pagerAdapter);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragment1.test();
            }
        }, 3000);


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "count::" + pager2.getChildCount());
                    }
                },1500);

            }
        });


    }

    //actionbar使用
    private void actionbarStyle() {
        ActionBar actionBar= getSupportActionBar();
        //主标题
        actionBar.setTitle("Demo大全");
        //副标题
        actionBar.setSubtitle("Fragment+ViewPager");
        //左侧按钮：可见+可用+更换图标
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        //actionBar.setHomeAsUpIndicator(R.mipmap.back_white);
        //设置logo
        actionBar.setLogo(android.R.mipmap.sym_def_app_icon);
        actionBar.setDisplayUseLogoEnabled(true);
        //设置icon：use logo instead of an icon
        //actionBar.setIcon(R.mipmap.ic_launcher);

    }

    /**
     * 复写：左侧按钮点击动作
     * android.R.id.home
     * v7 actionbar back event
     * 注意：如果复写了onOptionsItemSelected方法，则onSupportNavigateUp无用
     * */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
