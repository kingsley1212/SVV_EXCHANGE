package com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.buy;

import android.content.Context;

import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.buy.model.IOtcFabuBuyModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.buy.presenter.OtcFabuBuyPresenter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.buy.view.IOtcFabuBuyView;

/**
 * Created by lzj on 2018/7/24.
 */

public class OtcFabuBuyAct extends MvpActivity<IOtcFabuBuyView, IOtcFabuBuyModel, OtcFabuBuyPresenter> implements
        IOtcFabuBuyView {

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return 0;
    }

    @Override
    public void viewInitialization() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public OtcFabuBuyPresenter initPresenter() {
        return new OtcFabuBuyPresenter();
    }

    @Override
    public Context getMContext() {
        return null;
    }
}
