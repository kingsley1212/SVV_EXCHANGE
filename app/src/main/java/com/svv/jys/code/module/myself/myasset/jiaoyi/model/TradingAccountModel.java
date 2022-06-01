package com.svv.jys.code.module.myself.myasset.jiaoyi.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/26.
 */

public interface TradingAccountModel {
    Observable<BaseResponse> rx_getUserCoinInfo(GET_TRADEACCOUNT_RECORD_REQ req);
    Observable<BaseResponse> rx_getUserOTCInfo(GET_TRADEACCOUNT_RECORD_REQ req);
    Observable<BaseResponse> rx_getUserSCInfo(GET_TRADEACCOUNT_RECORD_REQ req);

    Observable<BaseResponse> rx_getNumBankList();
}
