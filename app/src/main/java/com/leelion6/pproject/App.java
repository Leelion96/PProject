package com.leelion6.pproject;

import android.app.Application;
import android.util.Log;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

/**
 * Created by LiCheng
 * Date：2018/11/15
 */
public class App extends Application {
    public App() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        UMConfigure.init(this, "5bed338ef1f5562ee100008d", "umeng",
                UMConfigure.DEVICE_TYPE_PHONE, "92ac4c4998e4d12d417b15cad5e216a7");
        //注册推送服务，每次调用register方法都会回调该接口
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("推送服务注册成功","返回的deviceToken是"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("推送服务注册失败","返回的错误信息是"+s);
            }
        });


    }
}
