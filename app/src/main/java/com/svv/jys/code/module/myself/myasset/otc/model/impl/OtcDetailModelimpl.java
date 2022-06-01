package com.svv.jys.code.module.myself.myasset.otc.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.otc.model.OtcDetailModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import rx.Observable;

/**
 * Created by js on 2018/6/7.
 */

public class OtcDetailModelimpl implements OtcDetailModel {
    @Override
    public Observable<BaseResponse> rx_getOtcCoinInfo(GET_USER_COIN_INFO req) {
        return API_Factory.GetOtcCoinInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_getOtcCoinList(GET_TRADEACCOUNT_RECORD_REQ req) {
        return API_Factory.GetOtcCoinlist(req);
    }
}
