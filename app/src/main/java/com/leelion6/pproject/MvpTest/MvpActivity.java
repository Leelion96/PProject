package com.leelion6.pproject.MvpTest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.leelion6.pproject.Base.BaseActivity;
import com.leelion6.pproject.R;

/**
 * Created by LiCheng
 * Date：2018/11/8
 */
public class MvpActivity extends BaseActivity implements MvpView{

    private TextView mMvpTest;
    private MvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //初始化presenter
        presenter = new MvpPresenter();
        presenter.attachView(this);
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
    public void showData(String data) {
        mMvpTest.setText(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    @Override
    public void bindUI(View rootView) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mMvpTest = findViewById(R.id.tv_mvp_test);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_test;
    }
}
