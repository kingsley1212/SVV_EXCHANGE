package com.svv.jys.code.common.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by lzj on 2017/7/1.
 */

public abstract class MvpFragment<V extends BaseView, M, P extends BasePresent<V, M>> extends BaseFragment {


    protected P presenter;

    public abstract P initPresenter();

    public abstract void onWakeBussiness();

    //是否执行唤醒监听
    public boolean wakeListener = true;

    public boolean isHidden = true, isFirstResume = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = initPresenter();
        presenter.attach((V) this);
        super.onCreate(savedInstanceState);
    }


    //唤醒
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHidden = hidden;
        if (presenter == null) {
            return;
        }
        if (!hidden && wakeListener) {
            onWakeBussiness();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstResume && !isHidden && wakeListener) {
            onWakeBussiness();
        }
        isFirstResume = false;
    }

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }
}
