package com.leelion6.pproject.HandlerTest;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leelion6.pproject.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandlerActivity extends AppCompatActivity {

    @BindView(R.id.tv_handler)
    TextView tvHandler;
    @BindView(R.id.iv_handler)
    ImageView ivHandler;
    @BindView(R.id.btn_asynctask)
    Button btnAsyncTask;
    @BindView(R.id.btn_method3)
    Button btnMethod3;
    @BindView(R.id.btn_method4)
    Button btnMethod4;
    @BindView(R.id.btn_method2)
    Button btnMethod2;
    @BindView(R.id.btn_method1)
    Button btnMethon1;
    @BindView(R.id.pb_handler)
    ProgressBar pbHandler;

    private String mThreadName;
    private Runnable mRunnable;
    private Handler mHandler;
    protected String mUrl = "http://imgsrc.baidu.com/imgad/pic/item/ca1349540923dd5471f1e241da09b3de9c82481c.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                //给图片添加随机颜色
                int ranColor = 0xff000000 | new Random().nextInt(0x00ffffff);
                ivHandler.setImageDrawable(new ColorDrawable(ranColor));
                tvHandler.setText(mThreadName);

            }
        };
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                int ranColor = 0xff000000 | new Random().nextInt(0x00ffffff);
                ivHandler.setImageDrawable(new ColorDrawable(ranColor));
                tvHandler.setText(mThreadName);
                return false;
            }
        });



    }


    @OnClick({R.id.btn_method3, R.id.btn_method4, R.id.btn_method2, R.id.btn_method1, R.id.btn_asynctask})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_method1:
                //需要另开线程处理数据以免阻塞UI线程，像是IO操作或者是循环，可以使用Activity.runOnUiThread()
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        mThreadName = "调用线程----Thread.Name = " + Thread.currentThread().getName();
                        runOnUiThread(mRunnable);
                    }
                }.start();
                break;
            case R.id.btn_method2:
                //直接执行Runnable进行操作，实际上Runnable对象会被封装成Message对象
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        mThreadName = "调用线程----Thread.Name = " + Thread.currentThread().getName();
                        mHandler.post(mRunnable);
                    }
                }.start();
                break;
            case R.id.btn_method3:
                //如果需要传递状态值等信息，使用Handler.senMessage的方式
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        mThreadName = "调用线程----Thread.Name = " + Thread.currentThread().getName();
                        mHandler.sendEmptyMessage(1);
                    }
                }.start();
                break;
            case R.id.btn_method4:
                //如果只是单纯的想要更新UI而不涉及到多线程的话，使用View.post()就可以了
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        mThreadName = "调用线程----Thread.Name = " + Thread.currentThread().getName();
                        tvHandler.post(mRunnable);
                    }
                }.start();
                break;
            case R.id.btn_asynctask:
                new MyAsyncTask().execute(mUrl);

                break;
        }
    }


    //构建AsyncTask子类，模拟带进度条的加载图片操作
    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        //onPreExecute用于异步处理前的操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbHandler.setVisibility(View.VISIBLE);
        }

        //在doInBackground方法中进行异步任务的处理，只有此方法在
        @Override
        protected Bitmap doInBackground(String... strings) {
            publishProgress(0);
            String url = strings[0];

            mThreadName = "当前线程----Thread.Name = " + Thread.currentThread().getName();

            Bitmap bitmap = null;
            URLConnection connection;
            InputStream inputStream;
            try {
                connection = new URL(url).openConnection();
                inputStream = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 100; i++) {
                //模拟长时间任务进度条更新
                publishProgress(i);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return bitmap;
        }

        //通过publishProgress方法对传过来的值进行进度条的更新.
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbHandler.setProgress(values[0]);
        }

        //onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            pbHandler.setVisibility(View.INVISIBLE);
            ivHandler.setImageBitmap(bitmap);
            tvHandler.setText(mThreadName);
        }
    }
}
