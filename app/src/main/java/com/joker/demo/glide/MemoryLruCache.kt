package com.joker.demo.glide

import android.graphics.Bitmap
import android.util.LruCache
import com.joker.demo.glide.cache.BitmapCache

class MemoryLruCache private constructor():BitmapCache{

    var activityCache:HashMap<String,Int>?=null
    var lruCache:LruCache<String,Bitmap>?=null

    init{
        var maxMemorySize=1024*1024*1024
        lruCache=object :LruCache<String,Bitmap>(maxMemorySize){
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return value!!.rowBytes*value?.height
            }
        }
        activityCache= HashMap()
    }

    companion object{
        private var instance: MemoryLruCache? = null
            get() {
                if (field == null) {
                    field = MemoryLruCache()
                }
                return field
            }

        @Synchronized
        fun get(): MemoryLruCache {
            return instance!!
        }

    }

    override fun put(request: BitmapRequest, bitmap: Bitmap) {
        lruCache?.put(request.urlMd5,bitmap)
        activityCache?.put(request.urlMd5,request.context.hashCode())
    }

    override fun get(request: BitmapRequest): Bitmap {
        return lruCache?.get(request.urlMd5)!!
    }

    override fun remove(request: BitmapRequest) {
        lruCache?.remove(request.urlMd5)
    }

    //只有activity被销毁了
    override fun remove(activity: Int) {
        var tempUrlMD5List=ArrayList<String>()
        for(urlMd5 in activityCache!!.keys){
            if(activity==activityCache!!.get(urlMd5)!!.toInt()){
                tempUrlMD5List.add(urlMd5)
            }
        }

        //回收内存
        for(urlMd5 in tempUrlMD5List){
            activityCache!!.remove(urlMd5)
            var bitmap=lruCache?.get(urlMd5)
            if(bitmap!=null&&!bitmap.isRecycled){
                bitmap.recycle()
            }
            bitmap=null
        }
        if(!tempUrlMD5List.isEmpty()){
            System.gc()
        }
    }
}