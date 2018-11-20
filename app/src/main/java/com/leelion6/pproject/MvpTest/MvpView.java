package com.leelion6.pproject.MvpTest;

import com.leelion6.pproject.Base.IView;

/**
 * Created by LiCheng
 * Date：2018/11/8
 */
public interface MvpView extends IView {

    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data 数据源
     */
    void showData(String data);

}
