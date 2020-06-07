package com.joker.demo.fragment

import android.app.FragmentTransaction
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.joker.demo.R

class FragmentTestActivity : AppCompatActivity(),StaticFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)
        var tvStaticFragment = findViewById<TextView>(R.id.tv_static_fragment)

        var button = findViewById<Button>(R.id.btn_change_text)
        /*button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                tv.setText("文本改变了")
            }
        })*/
        button.setOnClickListener {tvStaticFragment.setText("文案改变了")  }
        //动态加载fragment
        val dynamicFragment=DynamicFragment()
       /* FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add();
        fragmentTransaction.commit()*/;
        val beginTransaction=supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_container,dynamicFragment)
        beginTransaction.commit()
    }



    override fun onFragmentInteraction(uri: Uri) {
        TODO("Not yet implemented")
    }
}
