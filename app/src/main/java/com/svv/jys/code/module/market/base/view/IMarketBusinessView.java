package com.svv.jys.code.module.market.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.MarketTitleEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface IMarketBusinessView extends BaseView {
    void setMarketTitle(MarketTitleEntity list);
    void setMarketList(List<MarketListEntity> list);

    List<MarketListEntity> getNowShowMarket();

    void setMarket(List<MarketListEntity> list);
}
