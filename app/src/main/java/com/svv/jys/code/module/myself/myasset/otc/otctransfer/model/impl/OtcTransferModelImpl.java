package com.svv.jys.code.module.myself.myasset.otc.otctransfer.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.model.OtcTransferModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;
import com.svv.jys.code.module.net.req.POST_OTC_COIN;
import com.svv.jys.code.module.net.req.POST_OTC_TRANSFER_REQ;

import rx.Observable;

/**
 * Created by js on 2018/6/7.
 */

public class OtcTransferModelImpl implements OtcTransferModel {
    @Override
    public Observable<BaseResponse> rx_otcCoin(POST_OTC_COIN req) {
        return API_Factory.API_GetotcCoin(req);
    }
    @Override
    public Observable<BaseResponse> rx_getOtcCoinInfo(GET_USER_COIN_INFO req) {
        return API_Factory.GetOtcCoinInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_otcTransfer(POST_OTC_TRANSFER_REQ req) {
        return API_Factory.GetOtcTransfer(req);
    }
}
