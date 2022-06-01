package com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.model.OtcOrderModel1;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_OTC_ORDER_REQ;

import rx.Observable;

/**
 * Created by js on 2018/9/4.
 */


public class OtcOrderModelImpl1 implements OtcOrderModel1 {
    @Override
    public Observable<BaseResponse> rx_getOtcOrder(GET_OTC_ORDER_REQ req) {
        return API_Factory.GetOtcOrder(req);
    }
}
