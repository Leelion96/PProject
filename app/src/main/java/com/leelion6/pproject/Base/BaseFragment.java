package com.leelion6.pproject.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LiCheng
 * Date：2018/11/8
 */
public abstract class BaseFragment extends Fragment implements IView {

    public abstract int getContentViewId();

    protected  abstract void initAllMembersView(Bundle savedInstanceState);

    protected Context mContext;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(),container,false);
        this.mContext = getActivity();
        initAllMembersView(savedInstanceState);
        return mRootView;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showErr() {
        checkActivityAttached();
    }

    protected boolean isAttachedContext(){
        return getActivity() != null;
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached(){
        if (getActivity()==null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException{
        public ActivityNotAttachedException(){
            super("Fragment has disconnected from Activity !");
        }
    }


}
