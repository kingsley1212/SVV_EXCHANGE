package com.svv.jys.code.module.market.slidemenu.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.market.slidemenu.model.SlideMenuModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/17.
 */

public class SlideMenuModelIpml implements SlideMenuModel {
    @Override
    public Observable<BaseResponse> rx_getMarketTitle(POST_KONG_REQ req) {
        return API_Factory.API_DoGetMarketArea(req);
    }
    @Override
    public Observable<BaseResponse> rx_GetMarketList(GET_MARKET_LIST_REQ req) {
        return API_Factory.API_DoGetMarketList(req);
    }
}
