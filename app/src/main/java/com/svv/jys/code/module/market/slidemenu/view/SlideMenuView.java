package com.svv.jys.code.module.market.slidemenu.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.MarketTitleEntity;

import java.util.List;

/**
 * Created by js on 2018/5/17.
 */

public interface SlideMenuView extends BaseView {
    void setMenu(MarketTitleEntity list);

    List<MarketListEntity> getNowShowMarket();

    void setMarket(List<MarketListEntity> list);
}
