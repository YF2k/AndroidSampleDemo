package com.joker.demo.glide

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.joker.demo.utils.MD5Utils
import java.lang.ref.SoftReference

class BitmapRequest(context: Context){
    var url=""
    var imageView:SoftReference<ImageView>?=null//为什么用软引用
    var urlMd5=""//1.防止图片错位  2.做缓存的key更合适
    var context:Context?=null
    var loadingResId:Int=0
    var requestListener:RequestListener?=null

    fun loading(loadingResId:Int):BitmapRequest{
        this.loadingResId=loadingResId
        return this
    }

    fun load(url:String):BitmapRequest{
        this.url=url
        this.urlMd5=MD5Utils.md5Encrypt(url)
        return this
    }

    fun listener(requestListener: RequestListener):BitmapRequest{
        this.requestListener=requestListener
        return this
    }

    fun into(imageView: ImageView){
        this.imageView= SoftReference<ImageView>(imageView)
        imageView.setTag(urlMd5)//bitmap复用
        //将请求加入队列
        RequestManager.get().addBitmapRequest(this)
    }

   fun getImageView(): ImageView? {
       return imageView?.get()
   }

}