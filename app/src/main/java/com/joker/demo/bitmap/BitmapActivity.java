package com.joker.demo.bitmap;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;

import java.io.IOException;
import java.io.InputStream;

public class BitmapActivity extends BaseActivity {
    private static final String TAG = "BitmapActivity";
    ImageView mIvBitmap;
    int resIndex;

    @BindView(R.id.btn_switch_image)
    Button mBtnSwitchImage;

    @BindView(R.id.id_largeImageview)
    LargeImageView mLargeImageView;

    private Unbinder unbinder;

    Bitmap reuseBitmap;//可以用来复用的bitmap对象

    int[] resIds = {R.drawable.bmp, R.drawable.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        unbinder = ButterKnife.bind(this);
        initFromDrawable(true);
        initFromAssets();
        showRegionImage();
        initLargeImage();
    }

    private void initLargeImage() {
        try {
            InputStream inputStream = getAssets().open("qm.jpg");
            mLargeImageView.setInputStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_switch_image)
    void switchImage() {
        mIvBitmap.setImageBitmap(getBitmap());
    }

    //分片显示
    void showRegionImage() {
        try {
            InputStream inputStream = getAssets().open("long_bmp.jpg");
            //设置显示图片的中心区域
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = decoder.decodeRegion(new Rect(0, 0, 200, 200), options);
            mIvBitmap.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //bitmap复用
    public Bitmap getBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resIds[resIndex % 2], options);
        if (canUserForInBitmap(reuseBitmap, options)) {
            options.inMutable = true;//这里如果不置为 true 的话，BitmapFactory 将不会重复利用 Bitmap 内存,
            // 并输出相应 warning 日志：Unable to reuse an immutable bitmap as an image decoder target.
            options.inBitmap = reuseBitmap;//将 options.inBitmap 赋值为之前创建的 reuseBitmap 对象，从而避免重新分配内存。
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), resIds[resIndex++ % 2], options);
    }

    //判断 reuseBitmap 是否可以被复用
    //在 Android 4.4 版本之前，只能重用相同大小的 Bitmap 内存区域；
    //4.4 之后你可以重用任何 Bitmap 的内存区域，只要这块内存比将要分配内存的 bitmap 大就可以。
    private boolean canUserForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        int width = targetOptions.outWidth / Math.max(targetOptions.inSampleSize, 1);
        int height = targetOptions.outWidth / Math.max(targetOptions.inSampleSize, 1);
        int byteCount = width * height * getBytesPerPixel(candidate.getConfig());
        boolean isCanUse = byteCount < candidate.getAllocationByteCount();//新的bitmap内存<可复用BItmap占用内存
        Log.d(TAG, "是否可以复用:" + isCanUse);
        return isCanUse;
    }

    private int getBytesPerPixel(Bitmap.Config config) {
        int bytePerPixel;
        switch (config) {
            case ALPHA_8:
                bytePerPixel = 1;
                break;
            case RGB_565:
            case ARGB_4444:
                bytePerPixel = 2;
                break;
            default:
                bytePerPixel = 4;
                break;
        }
        return bytePerPixel;
    }


    private void initFromAssets() {
        try {
            InputStream inputStream = getAssets().open("bmp.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Log.i(TAG, "bitmap size is:" + bitmap.getAllocationByteCount());//bitmap size is:10886400
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFromDrawable(boolean isPreferred) {
        BitmapFactory.Options options = null;
        //加载优化
        if (isPreferred) {
            options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            //采样优化
            options.inSampleSize = 2;//宽和高每隔两个像素进行一次采样
        }


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bmp, options);//1080X2520   768.06k
        Log.i(TAG, "bitmap size is:" + bitmap.getAllocationByteCount());//bitmap size is:24494400(xh) 10886400(ssh-默认)
        //默认情况下 BitmapFactory 使用 Bitmap.Config.ARGB_8888 的存储方式来加载图片内容，而在这种存储模式下，每一个像素需要占用 4 个字节
        //bitmap size is=1080X2520X4

        //BitmapFactory 在解析图片的过程中，会根据当前设备屏幕密度和图片所在的 drawable 目录来做一个对比，根据这个对比值进行缩放操作
        //缩放比例 scale = 当前设备屏幕密度 / 图片所在 drawable 目录对应屏幕密度
        //Bitmap 实际大小 = 宽 * scale * 高 * scale * Config 对应存储像素数
        mIvBitmap.setImageBitmap(bitmap);
    }

    protected void initView() {
        setContentView(R.layout.activity_bitmap);
        mIvBitmap = findViewById(R.id.iv_bitmap);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        reuseBitmap = BitmapFactory.decodeResource(getResources(), resIds[0], options);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
