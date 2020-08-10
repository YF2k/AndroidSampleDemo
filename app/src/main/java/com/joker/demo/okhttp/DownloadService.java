package com.joker.demo.okhttp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import com.joker.demo.R;

import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadService extends Service {

    private String downloadUrl;
    private DownLoadBinder mBinder = new DownLoadBinder();
    private DownloadTask downloadTask;
    DownLoadListener downLoadListener = new DownLoadListener() {


        /**
         * 构建了一个用于显示下载进度的通知
         * @param progress
         */
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        /**
         * 创建了一个新的通知用于告诉用户下载成功啦
         */
        @Override
        public void onSuccess() {
            downloadTask = null;
//下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            //下载失败时，将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Download Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            //取消下载，将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Download Canceled", Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 获取NotificationManager的实例，对通知进行管理
     *
     * @return
     */
    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 为了要让DownloadService可以和活动进行通信，我们创建了一个DownloadBinder对象
     */

    class DownLoadBinder extends Binder {
        //开始下载
        public void startDownload(String url) {
            downloadUrl=url;
            downloadTask = new DownloadTask(downLoadListener);
            //启动下载任务
            downloadTask.execute(url);
            startForeground(1, getNotification("Downloading...", 0));
            Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
        }

        /**
         * 暂停下载
         */
        public void pauseDownload(){
            if(downloadTask!=null){
                downloadTask.pauseDownload();
            }
        }

        /**
         * 取消下载
         */
        public void cancelDownload(){
            if(downloadTask!=null){
                downloadTask.cancelDownload();
            }else {
                if(downloadUrl!=null){
                    //取消下载时需要将文件删除，并将通知关闭
                    String fileName=downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory= Environment.getRootDirectory().getAbsolutePath();
                    File file=new File(directory+fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    /**
     * @param title
     * @param progress
     * @return
     */
    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, NetTestActivity.class);
        //PendingIntent是等待的Intent,这是跳转到一个Activity组件。当用户点击通知时，会跳转到MainActivity
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        /**
         * 几乎Android系统的每一个版本都会对通知这部分功能进行获多或少的修改，API不稳定行问题在通知上面凸显的尤其严重。
         * 解决方案是：用support库中提供的兼容API。support-v4库中提供了一个NotificationCompat类，使用它可以保证我们的
         * 程序在所有的Android系统版本中都能正常工作。
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //设置通知的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //设置通知的大图标，当下拉系统状态栏时，就可以看到设置的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        //当通知被点击的时候，跳转到MainActivity中
        builder.setContentIntent(pi);
        //设置通知的标题
        builder.setContentTitle(title);
        if (progress > 0) {
            //当progress大于或等于0时，才需要显示下载进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

}
