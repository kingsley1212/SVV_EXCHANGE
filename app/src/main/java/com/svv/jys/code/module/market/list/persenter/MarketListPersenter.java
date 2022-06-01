package com.svv.jys.code.module.market.list.persenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.entity.MarketTitleEntity;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;
import com.svv.jys.code.module.market.list.model.MarketListModel;
import com.svv.jys.code.module.market.list.model.impl.MarketListModelImpl;
import com.svv.jys.code.module.market.list.view.MarketListView;

/**
 * Created by js on 2018/5/19.
 */

public class MarketListPersenter extends BasePresent<MarketListView, MarketListModel> {

    public MarketTitleEntity select_mt;

    public MarketListPersenter() {
        model = new MarketListModelImpl();
    }

    public void doHandleMarketList(MarketListEvent marketListEvent) {

    }
}
