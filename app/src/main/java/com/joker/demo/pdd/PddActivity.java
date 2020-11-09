package com.joker.demo.pdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;
import com.joker.demo.utils.HttpUtils;
import com.joker.demo.utils.JSUtil;
import com.joker.demo.utils.PddTextUtil;

import java.io.IOException;
import java.util.Map;

//拼多多手机优惠券相关API
//todo:bug fix
public class PddActivity extends BaseActivity {
    Button mBtnStart,mBtnEnd,mBtngetAntiContent;
    TextView mTvResponse;

    String xcx_hash = System.currentTimeMillis() + PddTextUtil.GetRandstr(16, 2);

    String url = "https://api.pinduoduo.com/api/promotion/take_merchant_coupon?pdduid=3928652364&xcx=20161201&xcx_version=v6.9.3&xcx_hash="+xcx_hash;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            fangwenApi(url);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdd);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnEnd = findViewById(R.id.btn_end);
        mBtngetAntiContent = findViewById(R.id.btn_get_antiContent);
        mTvResponse = findViewById(R.id.tv_response);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始访问接口
                fangwenApi(url);
                mHandler.sendEmptyMessageDelayed(0,10);
            }
        });

        mBtngetAntiContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                fangwenApi("https://www.baidu.com/");
                showToast(JSUtil.loadJsFile(PddActivity.this));

            }
        });
    }

    //post
    private void fangwenApi(String strUrl){
        OkHttpClient client = new OkHttpClient();
        //post builder 参数
        FormBody.Builder builder = new FormBody.Builder();
//        builder.add(k,v);
        Request request = new Request.Builder()
                .url(strUrl).post(builder.build()).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToast("接口访问失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                showToast(response.body().string());
            }
        });
    }


    private void showToast(String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PddActivity.this,msg,Toast.LENGTH_LONG).show();
                mTvResponse.setText(msg);
            }
        });
    }


}