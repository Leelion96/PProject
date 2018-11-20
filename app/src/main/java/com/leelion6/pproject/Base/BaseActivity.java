package com.leelion6.pproject.Base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.leelion6.pproject.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LiCheng
 * Date：2018/11/8
 */
public abstract class BaseActivity extends AppCompatActivity implements IView {


    public static final String TAG = "BaseActivity";

    //进度条
    private ProgressDialog mProgressDialog;

    private Activity mContext;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Log.e(TAG,"current:" + BaseActivity.class.getSimpleName());

        if(getLayoutId()>=0){
            setContentView(getLayoutId());
            bindUI(null);
            //bindEvent();
        }


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void bindUI(View rootView) {
        mUnbinder = ButterKnife.bind(this);
    }







    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErr() {
        showToast(getResources().getString(R.string.api_error_msg));
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
