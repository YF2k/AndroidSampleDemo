package com.joker.demo.memoryoptimization

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.joker.demo.R


//内存泄漏代码---非静态内部类/匿名内部类会持有外部类引用（handler）
class HandlerActivity : AppCompatActivity() {
    val HANDLER_FLAG=666

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadler)
        handler.sendEmptyMessageDelayed(HANDLER_FLAG,1000)
    }

    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            sendEmptyMessageDelayed(HANDLER_FLAG, 1000)
        }
    }


   /* 解决内存泄露
    override fun onDestroy() {
        super.onDestroy()
        handler.removeMessages(HANDLER_FLAG)
    }*/
}
