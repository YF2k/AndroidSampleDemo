package com.joker.demo.jetpack.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joker.demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RoomActivity";
    UserDao mUserDao;
    TextView mTvMsg;
    String msg;
    Button mBtnInsertOne,mBtnInsertSome,mBtnUOne,mBtnDOne,mBtnQOne,mBtnQAll,mBtnDAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        initView();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "roomDemo-database")
                //下面注释表示允许主线程进行数据库操作，但是不推荐这样做。
                //他可能造成主线程lock以及anr
                //所以我们的操作都是在新线程完成的
                //.allowMainThreadQueries()
                .build();

        //得到userDao对象
        mUserDao = db.userDao();
    }

    private void initView() {
        mTvMsg = findViewById(R.id.tv_msg);
        mBtnInsertOne=findViewById(R.id.btn_insert_one);
        mBtnInsertSome=findViewById(R.id.btn_insert_one);
        mBtnUOne=findViewById(R.id.btn_update_one);
        mBtnDOne=findViewById(R.id.btn_delete_one);
        mBtnQOne=findViewById(R.id.btn_find_one);
        mBtnQAll=findViewById(R.id.btn_find_all);
        mBtnDAll=findViewById(R.id.btn_delete_all);

        mBtnInsertOne.setOnClickListener(this);
        mBtnInsertSome.setOnClickListener(this);
        mBtnUOne.setOnClickListener(this);
        mBtnDOne.setOnClickListener(this);
        mBtnQOne.setOnClickListener(this);
        mBtnQAll.setOnClickListener(this);
        mBtnDAll.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert_one:
                //防止UI线程阻塞以及ANR,所有的数据库操作，推荐开启新的线程访问。
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //返回的是插入元素的primary key index
                        Long aLong = mUserDao.insert(new User());
                        if (aLong > 0) {
                            msg = "insert one success, index is " + aLong.toString();
                            Log.i(TAG, msg);
                        } else {
                            msg = "insert one fail ";
                            Log.i(TAG, msg);
                        }
                        RoomActivity.this.setMsg();
                    }
                }).start();
                break;
            case R.id.btn_insert_some:
                msg="";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<User> users = new ArrayList<>();
                        users.add(new User());
                        users.add(new User());
                        List<Long> longs = mUserDao.insertAll(users);
                        if (longs != null && longs.size() > 0) {
                            for (Long aLong : longs) {
                                msg = msg+"insert some success, index is " + aLong+"\n";
                                Log.i(TAG, msg);
                            }
                        } else {
                            msg = "insert some fail ";
                            Log.i(TAG, msg);
                        }
                        RoomActivity.this.setMsg();
                    }
                }).start();
                break;
            case R.id.btn_update_one:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int random = new Random().nextInt(9) + 1;
                        int update = mUserDao.update(new User());
                        if (update > 0) {
                            msg = "update one success, index is " + random;
                            Log.i(TAG, msg);
                        } else {
                            msg = "update one fail ,index is " + random + " the user item doesn't exist ";
                            Log.i(TAG, msg);
                        }
                        RoomActivity.this.setMsg();
                    }
                }).start();
                break;
            case R.id.btn_delete_one:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int random = new Random().nextInt(9) + 1;
                        int delete = mUserDao.delete(new User(random));
                        if (delete > 0) {
                            //size 表示删除个数
                            msg = "delete one  success,index is " + random;
                            Log.i(TAG, msg);
                        } else {
                            msg = "delete  one fail ,index is " + random + " the user item doesn't exist ";
                            Log.i(TAG, msg);
                        }
                        RoomActivity.this.setMsg();

                    }
                }).start();

                break;
            case R.id.btn_find_one:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int random = new Random().nextInt(9) + 1;
                        User user = mUserDao.findByUid(random);
                        if (user != null) {
                            msg = "find one success , index is " + random + "  user:  " + user.toString();
                            Log.i(TAG, msg);
                        } else {
                            msg = "find one fail , index is " + random + " the user item doesn't exist ";
                            Log.i(TAG, msg);
                        }
                        RoomActivity.this.setMsg();
                    }
                }).start();
                break;
            case R.id.btn_find_all:
                msg="";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<User> all = mUserDao.getAll();
                        if (all != null && all.size() > 0) {
                            for (User user1 : all) {
                                msg = msg+"find all success ,item  : " + user1.toString()+"\n";
                                Log.i(TAG, msg);
                            }
                        } else {
                            msg = "find all fail , no user item  exist ";
                            Log.i(TAG, msg);
                        }
                        RoomActivity.this.setMsg();
                    }
                }).start();
                break;
            case R.id.btn_delete_all:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<User> all = mUserDao.getAll();
                        if (all != null && all.size() > 0) {
                            int i = mUserDao.deleteAll(all);
                            msg = "delete all success , delete  size " + i;
                            Log.i(TAG, msg);
                        } else {
                            msg = "delete all fail , no user item  exist ";
                            Log.i(TAG, msg);
                        }
                        RoomActivity.this.setMsg();
                    }
                }).start();
                break;
        }
    }

    private void setMsg() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvMsg.setText(msg);
            }
        });
    }
}
