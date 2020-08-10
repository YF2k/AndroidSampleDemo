package com.joker.demo.lru;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import android.os.Bundle;
import android.widget.ImageView;

import com.joker.demo.R;

public class DiskLruCacheActivity extends AppCompatActivity {
    ImageView mIvDiskLruBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk_lru_cache);

        initView();
        showBitmap();
    }

    private void showBitmap() {
        String url="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1046582473,3860904341&fm=26&gp=0.jpg";
        downloadImageFromUrl(url);
    }

    private void downloadImageFromUrl(String url) {
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
        Request request=new Request.Builder().url(url).build();
        Call call= okHttpClient.newCall(request);
//        call.enqueue();
    }


    private void initView() {
        mIvDiskLruBitmap=findViewById(R.id.iv_disklrucache_bitmap);
    }
}
