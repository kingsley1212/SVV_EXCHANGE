package com.svv.jys.code.module.splash.view;

import android.view.View;

import com.svv.jys.code.common.base.mvp.BaseView;


/**
 * Created by Administrator on 2018/4/14 0014.
 */

public interface ISplashView extends BaseView {
    void directToMainAct();

    View get_a_view();

    void downloadApp(String url);

    void stopTimerToMain();

    /**
     * 获取剩余时间
     * @return
     */
    int getSurplusTime();

    void finishActivity();


}
