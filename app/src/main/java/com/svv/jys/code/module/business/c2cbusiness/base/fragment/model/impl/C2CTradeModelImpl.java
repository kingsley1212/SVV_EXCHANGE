package com.svv.jys.code.module.business.c2cbusiness.base.fragment.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.business.c2cbusiness.base.fragment.model.IC2CTradeModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.C2CCoinInfoReq;

import rx.Observable;


/**
 * Created by js on 2018/7/11.
 */

public class C2CTradeModelImpl implements IC2CTradeModel {
    @Override
    public Observable<BaseResponse> rx_c2cCoinInfo(C2CCoinInfoReq req) {
        return API_Factory.c2cCoinInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_c2cbuyorsell(C2CBuyOrSellReq req) {
        return API_Factory.c2cBuyOrSell(req);
    }
}
