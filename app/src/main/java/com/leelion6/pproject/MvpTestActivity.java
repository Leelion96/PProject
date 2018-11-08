package com.leelion6.pproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leelion6.pproject.MVP.MvpPresenter;
import com.leelion6.pproject.MVP.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MvpTestActivity extends AppCompatActivity implements MvpView {

    //进度条
    ProgressDialog progressDialog;
    MvpPresenter presenter;
    @BindView(R.id.tv_mvp_test)
    TextView tvMvpTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_test);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在加载数据");

        //初始化Presenter
        presenter = new MvpPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //断开view引用
        presenter.detachView();
    }

    // button 点击事件调用方法
    public void getData(View view){
        presenter.getData("normal");
    }

    public void getDataForFailure(View view){
        presenter.getData("failure");
    }

    public void getDataForError(View view){
        presenter.getData("error");
    }

    @Override
    public void showLoading() {
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showData(String data) {
        tvMvpTest.setText(data);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        tvMvpTest.setText(msg);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this,"网络请求数据出现异常",Toast.LENGTH_SHORT).show();
        tvMvpTest.setText("网络请求数据出现异常");
    }


}
