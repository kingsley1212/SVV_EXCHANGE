package com.svv.jys.code.module.myself.myasset.otc.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import rx.Observable;

/**
 * Created by js on 2018/6/7.
 */

public interface OtcDetailModel {

    Observable<BaseResponse> rx_getOtcCoinInfo(GET_USER_COIN_INFO req);
    Observable<BaseResponse> rx_getOtcCoinList(GET_TRADEACCOUNT_RECORD_REQ req);
}
