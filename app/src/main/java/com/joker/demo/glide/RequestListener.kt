package com.joker.demo.glide

import android.graphics.Bitmap

interface RequestListener {
    fun onSuccess(bitmap: Bitmap)

    fun onFailure()
}