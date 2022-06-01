package com.svv.jys.code.module.myself.subscription.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;

import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import rx.Observable;

public interface SubscriptionModel {
    Observable<BaseResponse> rx_getSettingInfo();
    Observable<BaseResponse> rx_getC2CList(GetC2CRecordListReq req);
    Observable<BaseResponse> rx_subscription(C2CBuyOrSellReq req);
    Observable<BaseResponse> getcoindata(C2CBuyOrSellReq req);
}
