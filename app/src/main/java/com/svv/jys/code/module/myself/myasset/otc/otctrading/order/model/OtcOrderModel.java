package com.svv.jys.code.module.myself.myasset.otc.otctrading.order.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_OTC_ORDER_REQ;

import rx.Observable;

/**
 * Created by js on 2018/6/11.
 */

public interface OtcOrderModel {
    Observable<BaseResponse> rx_getOtcOrder(GET_OTC_ORDER_REQ req);
}
