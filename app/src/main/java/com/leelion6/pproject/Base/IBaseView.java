package com.leelion6.pproject.Base;

import android.content.Context;

/**
 * Created by LiCheng
 * Date：2018/11/7
 */
public interface IBaseView {

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
