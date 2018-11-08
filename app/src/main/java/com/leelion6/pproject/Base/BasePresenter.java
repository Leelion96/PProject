package com.leelion6.pproject.Base;

/**
 * Created by LiCheng
 * Date：2018/11/7
 */
public class BasePresenter<V extends IBaseView> {


    /**
     * 绑定的view
     */
    private V mvpView;


    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V mvpView){
        this.mvpView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView(){
        this.mvpView = null;
    }

    /**
     * 是否与view建立连接
     * 每次调用业务请求的时候都要首先调用此方法检查是否与view连接，避免空指针异常
     */
    public boolean isViewAttached(){
        return mvpView != null;
    }

    public V getView(){
        return mvpView;
    }


}
