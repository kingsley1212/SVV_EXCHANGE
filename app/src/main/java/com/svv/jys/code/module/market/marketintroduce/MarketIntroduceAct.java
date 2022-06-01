package com.svv.jys.code.module.market.marketintroduce;

import android.content.Context;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.module.market.marketintroduce.model.IMarketIntroduceModel;
import com.svv.jys.code.module.market.marketintroduce.presenter.MarketIntroducePresenter;
import com.svv.jys.code.module.market.marketintroduce.view.IMarketIntroduceView;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MarketIntroduceAct extends MvpActivity<IMarketIntroduceView, IMarketIntroduceModel, MarketIntroducePresenter> implements IMarketIntroduceView{
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public MarketIntroducePresenter initPresenter() {
        return new MarketIntroducePresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_marketintroduce;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.marketintroduce_title));
    }

    @Override
    public void doBusiness() {

    }
}
