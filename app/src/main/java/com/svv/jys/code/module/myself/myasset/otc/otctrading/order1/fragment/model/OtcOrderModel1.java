package com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_OTC_ORDER_REQ;

import rx.Observable;

/**
 * Created by js on 2018/9/4.
 */

public interface OtcOrderModel1 {
    Observable<BaseResponse> rx_getOtcOrder(GET_OTC_ORDER_REQ req);
}
