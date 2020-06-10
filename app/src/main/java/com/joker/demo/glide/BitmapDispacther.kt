package com.joker.demo.glide

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.LinkedBlockingQueue

class BitmapDispacther(var requestQueue: LinkedBlockingQueue<BitmapRequest>) : Thread() {
    val TAG="BitmapDispacther"
    var handler = Handler(Looper.getMainLooper())

    override fun run() {
        super.run()
        while (!isInterrupted()) {
            //先从队列中获取请求
            var request = requestQueue.take()
            //先设置一个占位图片
            showLoadingImage(request)
            //去网络加载图片
            var bitmap = findBitmap(request)
            //显示到你的imageView上
            showImage(request, bitmap)
        }
    }

    private fun showImage(request: BitmapRequest, bitmap: Bitmap) {
        handler.post {
            if (request.getImageView()?.getTag()?.equals(request.urlMd5)!!) {
                request.getImageView()?.setImageBitmap(bitmap)
                request.requestListener?.onSuccess(bitmap)
            }
        }


    }

    private fun findBitmap(request: BitmapRequest): Bitmap {
        //内存----磁盘----网络
        var bitmap = downLoadImage(request.url)
        return bitmap
    }

    //网络
    private fun downLoadImage(urlStr: String): Bitmap {
        Log.i(TAG,urlStr)
        var fos: FileOutputStream? = null
        var ins: InputStream? = null
        var bitmap: Bitmap? = null

        var url = URL(urlStr)
        var conn = url.openConnection() as HttpURLConnection
        conn?.connect()
        ins = conn?.inputStream
        bitmap = BitmapFactory.decodeStream(ins)

        ins?.close()
        fos?.close()
        return bitmap

    }

    //占位图片
    private fun showLoadingImage(request: BitmapRequest) {
        if (request.loadingResId > 0) {
            var imageView = request.getImageView()
            var resId = request.loadingResId
            handler.post(Runnable { imageView?.setImageResource(resId) })
        }
    }
}