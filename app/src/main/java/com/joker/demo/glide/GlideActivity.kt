package com.joker.demo.glide

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.joker.demo.R

class GlideActivity : AppCompatActivity() {
    var mScrollLine:LinearLayout?=null

    var mBtnLoadOne: Button?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        mScrollLine=findViewById(R.id.scrooll_line)
        mBtnLoadOne=findViewById(R.id.btn_loadOne)

        mBtnLoadOne?.setOnClickListener {
            var imageView=ImageView(this@GlideActivity)
            imageView.layoutParams=ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            mScrollLine?.addView(imageView)
            MyGlide.with(this@GlideActivity)
                    .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591722789790&di=2a918f88c8b21c5d2f63d8e34b1d203c&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg")
                    .listener(object:RequestListener{
                        override fun onSuccess(bitmap: Bitmap) {

                        }

                        override fun onFailure() {
                        }
                    })
                    .into(imageView)
        }

    }
}
