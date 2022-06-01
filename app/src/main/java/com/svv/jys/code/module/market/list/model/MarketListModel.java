package com.svv.jys.code.module.market.list.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/19.
 */

public interface MarketListModel {
    Observable<BaseResponse> rx_GetMarketList(GET_MARKET_LIST_REQ req);
}
