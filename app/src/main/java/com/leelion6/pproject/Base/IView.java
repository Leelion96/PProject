package com.leelion6.pproject.Base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by LiCheng
 * Date：2018/11/7
 */
public interface IView {

    /**
     * 绑定UI
     * @param rootView 页面文件
     */
    void bindUI(View rootView);

    /**
     * 绑定事件
     */
    //void bindEvent();

    /**
     * 初始化控件
     * @param savedInstanceState 保存的Bundle状态
     */
    void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化事件
     */
    //void initEvent();

    /**
     * 获取页面
     * @return 页面id
     */
    int getLayoutId();

    /**
     * 显示正在加载进度框
     */
    void showLoading();

    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();

    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);

    /**
     * 显示请求错误提示
     */
    void showErr();

    /**
     * 获取Context
     * @return Context
     */
    Context getContext();


}
