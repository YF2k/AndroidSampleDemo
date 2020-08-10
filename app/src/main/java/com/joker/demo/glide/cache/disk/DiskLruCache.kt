package com.joker.demo.glide.cache.disk

import java.io.File
import java.io.InputStream
import java.io.OutputStream

class DiskLruCache {

    companion object{
        fun open(cacheFile:File,appVersion:Int,valueCount:Int,maxDiskSize:Int): DiskLruCache? {
            return null
        }
    }

    fun edit(urlMd5:String): Editor? {
        return null
    }

    fun get(key: String): Snapshot? {
        return null
    }

    class Editor{

        fun newOutputStream(index:Int): OutputStream? {
            return null
        }

        fun commit(){

        }

        fun abort(){


        }
    }

    class Snapshot{
        fun getInputStream(index:Int): InputStream? {
            return null
        }

    }
}