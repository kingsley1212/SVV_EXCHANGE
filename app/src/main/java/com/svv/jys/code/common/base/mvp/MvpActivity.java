package com.svv.jys.code.common.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by hankkin on 2017/3/29.
 */

public abstract class MvpActivity<V extends BaseView, M, P extends BasePresent<V, M>> extends BaseAcitvity {

    protected P presenter;

    public abstract P initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = initPresenter();
        presenter.attach((V) this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }




}
