package com.joker.demo.memoryoptimization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.joker.demo.BaseActivity
import com.joker.demo.R
import com.joker.demo.kotlin.Button

class MemoryShakeActivity : BaseActivity() {


    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            //创造内存抖动
            val range = 0..100
            for (index in range) {
                var list = arrayOfNulls<String>(100000)
            }
            sendEmptyMessageDelayed(0, 30)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_shake)
        var btnMemoryShake = findViewById<View>(R.id.btn_memory_shake)
        var btnMsActivity2 = findViewById<View>(R.id.btn_ms_activity2)
        btnMemoryShake.setOnClickListener { handler.sendEmptyMessage(0) }
        btnMsActivity2.setOnClickListener(
                object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        startTargetActivity(MemoryShake2Activity::class.java)
                    }
                })
    }


}
