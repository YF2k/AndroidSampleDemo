package com.joker.demo.glide

import java.util.concurrent.LinkedBlockingQueue

class RequestManager private constructor() {
    var requestQueue = LinkedBlockingQueue<BitmapRequest>()
    //初始化一个空数组，大小为0
    var bitmapDispacthers = emptyArray<BitmapDispacther?>()

    init {
        stop()
        createAndStartAllDispatcher()
    }

    //相当于食堂
    companion object {
        private var instance: RequestManager? = null
            get() {
                if (field == null) {
                    field = RequestManager()
                }
                return field
            }

        @Synchronized
        fun get(): RequestManager {
            return instance!!
        }
    }

    fun addBitmapRequest(request: BitmapRequest) {
        if (!requestQueue.contains(request)) {
            requestQueue.add(request)
        } else {
            //do noting

        }
    }

    fun stop() {
        if (bitmapDispacthers== null||bitmapDispacthers.size < 1) {
            return
        }
        for (i in 0 until bitmapDispacthers.size) {
            var bitmapDispacther = bitmapDispacthers[i]
            if (!bitmapDispacther?.isInterrupted()!!) {
                bitmapDispacther.interrupt()
            }
        }
    }

    fun createAndStartAllDispatcher() {
        var threadCount = Runtime.getRuntime().availableProcessors()
        //初始化一个空的数组，需要传入数组大小
        bitmapDispacthers = arrayOfNulls<BitmapDispacther>(threadCount)
        for (i in 0 until threadCount) {
            var bitmapDispacther = BitmapDispacther(requestQueue)
            bitmapDispacther.start()
            bitmapDispacthers[i] = bitmapDispacther
        }
    }

}