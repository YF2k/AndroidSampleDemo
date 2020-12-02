package com.joker.demo.opensourceframe.butterknife;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.joker.demo.R;

import java.util.List;

//https://blog.csdn.net/dingshuhong_/article/details/84322822
//https://www.jianshu.com/p/39fc66aa3297
public class ButterKnifeActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @BindView(R.id.tv_title)
    TextView mTvTitle;//@BindView fields must not be private or static.

    @BindViews({R.id.btn_submit1, R.id.btn_submit2, R.id.btn_submit3})
    List<Button> buttonList;

    @BindString(R.string.app_name)
    String appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        unbinder = ButterKnife.bind(this);

        buttonList.get(0).setText("button1");
        buttonList.get(1).setText("button2");
        buttonList.get(2).setText("button3");

    }


    @OnClick(R.id.btn_submit)
    public void submit() {
        mTvTitle.setText("hello world");
    }

    @OnClick(R.id.btn_submit1)
    public void submit1() {
        mTvTitle.setText(appName);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }


//adapter中使用butterknife
    /*public class SwordsmanAdapter extends ArrayAdapter<Swordsman> {
        @Override
        public View getView(int position,View convertView,ViewGroup parent) {
        }

        class ViewHolder {
            @BindView(R.id.tv_name)
            TextView tv_name;

            public ViewHolder(View view){
                ButterKnife.bind(this,view);
            }
        }
    }*/
}
