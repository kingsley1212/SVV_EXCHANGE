package com.svv.jys.code.module.myself.ordermanage.base.detailfragment.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.model.DetailModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_ORDER_DETAIL_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/24.
 */

public class DetailModelImpl implements DetailModel{
    @Override
    public Observable<BaseResponse> rx_getMarketTitle(POST_KONG_REQ req) {
        return API_Factory.API_DoGetMarketArea(req);
    }

    @Override
    public Observable<BaseResponse> rx_entrustDetail(GET_ORDER_DETAIL_REQ req) {
        return API_Factory.API_GetEntrustDetail(req);
    }
}
