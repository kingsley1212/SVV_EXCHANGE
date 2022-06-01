package com.svv.jys.code.module.market.detail;

import android.content.Context;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.module.market.detail.model.IMarketDetailModel;
import com.svv.jys.code.module.market.detail.presenter.MarketDetailPresenter;
import com.svv.jys.code.module.market.detail.view.IMarketDetailView;

/**
 * 行情
 * Created by Administrator on 2018/5/4 0004.
 */

public class MarketDetailAct extends MvpActivity<IMarketDetailView, IMarketDetailModel, MarketDetailPresenter> implements IMarketDetailView{
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public MarketDetailPresenter initPresenter() {
        return new MarketDetailPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_marketdetail;
    }

    @Override
    public void viewInitialization() {

    }

    @Override
    public void doBusiness() {

    }
}
