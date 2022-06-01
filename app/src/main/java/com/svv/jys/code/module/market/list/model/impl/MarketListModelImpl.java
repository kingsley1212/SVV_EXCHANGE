package com.svv.jys.code.module.market.list.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.market.list.model.MarketListModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/19.
 */

public class MarketListModelImpl implements MarketListModel {
    @Override
    public Observable<BaseResponse> rx_GetMarketList(GET_MARKET_LIST_REQ req) {
        return API_Factory.API_DoGetMarketList(req);
    }
}
