package com.svv.jys.code.module.market.detail.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.module.market.detail.model.IMarketDetailModel;
import com.svv.jys.code.module.market.detail.model.impl.MarketDetailModelImpl;
import com.svv.jys.code.module.market.detail.view.IMarketDetailView;

/**
 * Created by Administrator on 2018/5/4 0004.
 */

public class MarketDetailPresenter extends BasePresent<IMarketDetailView, IMarketDetailModel> {
    public MarketDetailPresenter() {
        model = new MarketDetailModelImpl();
    }
}
