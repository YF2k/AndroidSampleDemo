package com.joker.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.joker.demo.bitmap.BitmapActivity;
import com.joker.demo.butterknife.ButterKnifeActivity;
import com.joker.demo.butterknife.XyKnifeActivity;
import com.joker.demo.content.RequestCodes;
import com.joker.demo.customview.CustomViewActivity;
import com.joker.demo.fragment.FragmentTestActivity;
import com.joker.demo.glide.GlideActivity;
import com.joker.demo.jetpack.lifecycle.LifeCycleActivity;
import com.joker.demo.jetpack.room.RoomActivity;
import com.joker.demo.leakcanary.MemoryLeakActivity;
import com.joker.demo.memoryoptimization.HandlerActivity;
import com.joker.demo.memoryoptimization.MemoryShakeActivity;
import com.joker.demo.okhttp.NetTestActivity;
import com.joker.demo.startactivityforresult.AActivity;
import com.joker.demo.utils.PermissionUtil;
import com.joker.demo.viewpager2.Vp2FragmentActivity;

import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    Button mBtnA, mBtnVp2, mBtnCustomView, mBtnStartAtyFrST, mBtnLifeCycle, mBtnRoom, mBtnXyKnife;

    EditText mEtTest;

    @BindViews({R.id.btn_bitmap, R.id.btn_fragment})
    List<Button> mButtons;

    private Unbinder unbinder;

    final int what = 1;

    private Handler mHandler1 = new Handler() {
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            if (msg.what == what) {
                Log.d(TAG, "thread:" + Thread.currentThread().getName());
            }
        }
    };

    private Handler mHandler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtil.checkPermissionAndOpen(MainActivity.this, RequestCodes.REQUEST_CODE_CHECK_PERMISSION, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        initActionBar();
        initView();
        unbinder = ButterKnife.bind(this);
        mBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        Log.e("xiang", "A create");
        handlerTest();
    }

    private void handlerTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler1.sendEmptyMessage(what);

                mHandler2.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "post thread:" + Thread.currentThread().getName());
                    }
                });
            }
        }).start();


    }


    @OnClick({R.id.btn_bitmap, R.id.btn_fragment, R.id.btn_glide, R.id.btn_handler_activity, R.id.btn_memoryshake_activity, R.id.btn_network_activity, R.id.btn_memoryleak_activity})
    void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bitmap:
                startTargetActivity(BitmapActivity.class);
                break;
            case R.id.btn_fragment:
                startTargetActivity(FragmentTestActivity.class);
                break;
            case R.id.btn_glide:
                startTargetActivity(GlideActivity.class);
                break;
            case R.id.btn_handler_activity:
                startTargetActivity(HandlerActivity.class);
                break;
            case R.id.btn_memoryshake_activity:
                startTargetActivity(MemoryShakeActivity.class);
                break;
            case R.id.btn_network_activity:
                startTargetActivity(NetTestActivity.class);
                break;
            case R.id.btn_memoryleak_activity:
                startTargetActivity(MemoryLeakActivity.class);
                break;
        }
    }
//    void skipBitmap(){
//        startTargetActivity(BitmapActivity.class);
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_vp2:
                startActivity(new Intent(MainActivity.this, Vp2FragmentActivity.class));
                break;
            case R.id.btn_custom_view:
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                break;
            case R.id.btn_startActivityForResult:
                startTargetActivity(AActivity.class);
                break;
            case R.id.btn_lifecycle:
                startTargetActivity(LifeCycleActivity.class);
                break;
            case R.id.btn_room:
                startTargetActivity(RoomActivity.class);
                break;
            case R.id.btn_xyKnife:
                startTargetActivity(XyKnifeActivity.class);
                break;
        }
    }

    protected void initView() {
        setContentView(R.layout.activity_main);
        mBtnA = findViewById(R.id.btn_A);
        mBtnVp2 = findViewById(R.id.btn_vp2);
        mBtnCustomView = findViewById(R.id.btn_custom_view);
        mBtnStartAtyFrST = findViewById(R.id.btn_startActivityForResult);
        mBtnLifeCycle = findViewById(R.id.btn_lifecycle);
        mBtnRoom = findViewById(R.id.btn_room);
        mBtnXyKnife = findViewById(R.id.btn_xyKnife);
        mEtTest = findViewById(R.id.et_test);

        mBtnVp2.setOnClickListener(this);
        mBtnCustomView.setOnClickListener(this);
        mBtnStartAtyFrST.setOnClickListener(this);
        mBtnLifeCycle.setOnClickListener(this);
        mBtnRoom.setOnClickListener(this);
        mBtnXyKnife.setOnClickListener(this);

    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("目录页");
        actionBar.setSubtitle("moveTaskToBack");

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
