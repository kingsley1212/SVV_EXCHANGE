package com.svv.jys.code.common.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lzj on 2017/7/1.
 */

public abstract class BaseFragment extends Fragment {

    public View mainView;

    /**
     * 基本初始化工作放在这个方法 如 P类
     */
    public abstract void baseInitialization();

    public abstract int setR_Layout();

    /**
     * 控件初始化工作放在这个方法
     */
    public abstract void viewInitialization();

    /**
     * 业务逻辑放在这个方法 如获取网络数据
     */
    public abstract void doBusiness();




    //setUserVisibleHint是在onCreateView之前调用的
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseInitialization();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        //   return super.onCreateView(inflater, container, savedInstanceState);
        super.onCreateView(inflater, container, savedInstanceState);
        if (mainView == null) {
            mainView = inflater.inflate(setR_Layout(), container, false);
            viewInitialization();
        }
        return mainView;
    }

    public View $(int resid) {
        if (mainView == null) {
            return null;
        }
        return mainView.findViewById(resid);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doBusiness();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainView = null;
    }


    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(getActivity(), clz);
        if (ex != null) intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            getActivity().finish();
        }
    }

}
