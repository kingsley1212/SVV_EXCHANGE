package com.svv.jys.code.module.market.marketintroduce.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.module.market.marketintroduce.model.IMarketIntroduceModel;
import com.svv.jys.code.module.market.marketintroduce.model.impl.MarketIntroduceModelImpl;
import com.svv.jys.code.module.market.marketintroduce.view.IMarketIntroduceView;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MarketIntroducePresenter extends BasePresent<IMarketIntroduceView, IMarketIntroduceModel>{
    public MarketIntroducePresenter() {
        model = new MarketIntroduceModelImpl();
    }
}
