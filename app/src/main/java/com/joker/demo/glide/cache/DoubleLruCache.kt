package com.joker.demo.glide.cache

import android.content.Context
import android.graphics.Bitmap
import com.joker.demo.glide.BitmapRequest
import com.joker.demo.glide.MemoryLruCache
import java.lang.RuntimeException

class DoubleLruCache(context: Context) : BitmapCache {

    var lruCache: MemoryLruCache? = null
    var bitmapCache: DiskBitmapCache? = null

    init {
        bitmapCache = DiskBitmapCache.get(context)
        lruCache = MemoryLruCache.get()
    }

    companion object {
        private var instance: DoubleLruCache? = null

        fun get(): DoubleLruCache {
            if (instance == null) {
                throw RuntimeException("请在application初始化")
            }
            return instance!!
        }

        fun get(context: Context):DoubleLruCache {
            if (instance == null) {
                instance=DoubleLruCache(context)
            }
            return instance!!
        }
    }

    override fun put(request: BitmapRequest, bitmap: Bitmap) {
        lruCache?.put(request, bitmap)
        bitmapCache?.put(request, bitmap)
    }

    override fun get(request: BitmapRequest): Bitmap {
        var bitmap = lruCache?.get(request)
        bitmap = bitmapCache?.get(request)
        lruCache?.put(request, bitmap!!)
        return bitmap!!
    }

    override fun remove(request: BitmapRequest) {
        TODO("Not yet implemented")
    }

    override fun remove(activityCode: Int) {
        TODO("Not yet implemented")
    }
}