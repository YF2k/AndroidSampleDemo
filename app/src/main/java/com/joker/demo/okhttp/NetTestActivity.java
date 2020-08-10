package com.joker.demo.okhttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.joker.demo.R;



//参考博客：https://blog.csdn.net/program_developer/article/details/56968821
public class NetTestActivity extends AppCompatActivity {


    private Unbinder unbinder;
    DownloadService.DownLoadBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_test);
        unbinder = ButterKnife.bind(this);
        //这一点至关重要，因为启动服务可以保证DownloadService一直在后台运行，绑定服务则可以让MaiinActivity和DownloadService
        //进行通信，因此两个方法的调用都必不可少。
        Intent intent=new Intent(NetTestActivity.this,DownloadService.class);
        startService(intent);//启动服务

        bindService(intent,connection,BIND_AUTO_CREATE);//绑定服务

        /**
         *运行时权限处理：我们需要再用到权限的地方，每次都要检查是否APP已经拥有权限
         * 下载功能，需要些SD卡的权限，我们在写入之前检查是否有WRITE_EXTERNAL_STORAGE权限,没有则申请权限
         */
        if(ContextCompat.checkSelfPermission(NetTestActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(NetTestActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }


    }

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (DownloadService.DownLoadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {


        }
    };

    @OnClick({R.id.btn_start_download, R.id.btn_pause_download, R.id.btn_cancel_download})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_download:
                String url="http://s.toutiao.com/UsMYE/";
                  binder.startDownload(url);
                break;
            case R.id.btn_pause_download:
                binder.pauseDownload();
                break;
            case R.id.btn_cancel_download:
                binder.cancelDownload();
                break;

        }
    }


    /**
     * 用户选择允许或拒绝后,会回调onRequestPermissionsResult
     * @param requestCode  请求码
     * @param permissions
     * @param grantResults  授权结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定服务
        unbindService(connection);
    }
}
