package com.joker.demo.glide.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.joker.demo.glide.BitmapRequest
import com.joker.demo.glide.cache.disk.DiskLruCache
import com.joker.demo.utils.IOUtil
import java.io.BufferedOutputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

class DiskBitmapCache private constructor(context: Context) : BitmapCache {
    var diskLruCache: DiskLruCache? = null
    var imageCachePath = "Image"
    val MB = 1024 * 1024
    val maxDiskSize = 50 * MB

    init {
        var cacheFile = getImageCacheFile(context, imageCachePath)
        if (!cacheFile.exists()) {
            cacheFile.mkdirs()
        }
        diskLruCache = DiskLruCache.open(cacheFile, getAppVersion(context), 1, maxDiskSize)
    }


    companion object {
        @Volatile
        private var instance: DiskBitmapCache? = null

        @Synchronized
        fun get(context: Context): DiskBitmapCache {
            if (instance == null) {
                instance = DiskBitmapCache(context)
            }
            return instance!!
        }

    }

    private fun getAppVersion(context: Context): Int {
        var manager = context.packageManager
        var packageInfo = manager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionCode
    }

    private fun getImageCacheFile(context: Context, imageCachePath: String): File {
        var path = ""
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = context.externalCacheDir?.path!!
        } else {
            path = context.cacheDir.path

        }
        return File(path + File.separator + imageCachePath)
    }

    override fun put(request: BitmapRequest, bitmap: Bitmap) {
        var editor: DiskLruCache.Editor? = null
        var outputStream: OutputStream? = null
        editor = diskLruCache?.edit(request.urlMd5)
        outputStream = editor?.newOutputStream(0)
        if (preseBitmap2Disk(outputStream, bitmap)) {
            editor?.commit()
        } else {
            editor?.abort()
        }
        IOUtil.closeQuietly(outputStream)

    }


    private fun preseBitmap2Disk(outputStream: OutputStream?, bitmap: Bitmap): Boolean {
        var bufferedOutputStream: BufferedOutputStream? = null
        try {
            bufferedOutputStream = BufferedOutputStream(outputStream)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream)
            bufferedOutputStream.flush()
            return true
        } catch (e:Exception){
            e.printStackTrace()
        }finally {
            IOUtil.closeQuietly(bufferedOutputStream)
        }

        return false
    }

    override fun get(request: BitmapRequest): Bitmap {
        var stream:InputStream?=null
        try {
            var snapshot:DiskLruCache.Snapshot=diskLruCache?.get(request.urlMd5)!!
            if(snapshot!=null){
                stream=snapshot.getInputStream(0)
                return BitmapFactory.decodeStream(stream)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }finally {

        }
        return null!!
    }

    override fun remove(request: BitmapRequest) {
        TODO("Not yet implemented")
    }

    override fun remove(activityCode: Int) {
        TODO("Not yet implemented")
    }

}