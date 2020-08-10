package com.joker.demo.glide.cache

import android.graphics.Bitmap
import com.joker.demo.glide.BitmapRequest

interface BitmapCache {
    //存入内存
    fun put(request: BitmapRequest,bitmap: Bitmap)

    //读取缓存图片
    fun get(request: BitmapRequest):Bitmap

    //清除缓存图片
    fun remove(request: BitmapRequest)

    //清除所属Activity的bitmap
    fun remove(activityCode:Int)
}