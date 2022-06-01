package com.svv.jys.code.module.market.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface IMarketBusinessModel {
    Observable<BaseResponse> rx_getMarketTitle(POST_KONG_REQ req);
    Observable<BaseResponse> rx_GetMarketList(GET_MARKET_LIST_REQ req);
}
