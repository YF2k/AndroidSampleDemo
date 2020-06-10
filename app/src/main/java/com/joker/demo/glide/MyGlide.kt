package com.joker.demo.glide

import android.content.Context

class MyGlide {

    companion object{
        fun with(context:Context):BitmapRequest{
            return BitmapRequest(context)
        }
    }
}