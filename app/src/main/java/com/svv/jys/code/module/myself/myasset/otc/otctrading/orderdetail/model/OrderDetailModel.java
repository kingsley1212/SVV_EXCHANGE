package com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.OrderDetailReq;
import com.svv.jys.code.module.net.req.POST_SHENSU_REQ;
import com.svv.jys.code.module.net.req.POST_STASUS_REQ;

import rx.Observable;

/**
 * Created by js on 2018/6/11.
 */

public interface OrderDetailModel {
    Observable<BaseResponse> rx_setStatus(POST_STASUS_REQ req);
    Observable<BaseResponse> rx_orderShensu(POST_SHENSU_REQ req);
    Observable<BaseResponse> rx_orderDetail(OrderDetailReq req);

}
