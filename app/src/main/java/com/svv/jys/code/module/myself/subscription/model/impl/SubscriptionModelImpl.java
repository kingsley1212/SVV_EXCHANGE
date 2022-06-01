package com.svv.jys.code.module.myself.subscription.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.subscription.model.SubscriptionModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import rx.Observable;

public class SubscriptionModelImpl implements SubscriptionModel {
    @Override
    public Observable<BaseResponse> rx_getSettingInfo() {
        return API_Factory.API_GetSettingInfo();
    }

    @Override
    public Observable<BaseResponse> rx_getC2CList(GetC2CRecordListReq req) {
        return API_Factory.API_getSubscriptionList(req);
    }

    @Override
    public Observable<BaseResponse> rx_subscription(C2CBuyOrSellReq req) {
        return API_Factory.API_subscription(req);
    }

    @Override
    public Observable<BaseResponse> getcoindata(C2CBuyOrSellReq req) {
        return API_Factory.getcoindata(req);
    }
}
