package com.svv.jys.code.module.myself.myasset.jiaoyi.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.jiaoyi.model.TradingAccountModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/26.
 */

public class TradingAccountModelImpl implements TradingAccountModel{


    @Override
    public Observable<BaseResponse> rx_getUserCoinInfo(GET_TRADEACCOUNT_RECORD_REQ req) {
        return API_Factory.GetWalletCoinInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_getUserOTCInfo(GET_TRADEACCOUNT_RECORD_REQ req) {
        return  API_Factory.GetWalletOtcInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_getUserSCInfo(GET_TRADEACCOUNT_RECORD_REQ req) {
        return  API_Factory.GetWalletSCInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_getNumBankList() {
        return API_Factory.getNumBankList();
    }
}
