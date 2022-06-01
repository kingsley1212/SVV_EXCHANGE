package com.svv.jys.code.module.myself.myasset.otc.otctransfer.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;
import com.svv.jys.code.module.net.req.POST_OTC_COIN;
import com.svv.jys.code.module.net.req.POST_OTC_TRANSFER_REQ;

import rx.Observable;

/**
 * Created by js on 2018/6/7.
 */

public interface OtcTransferModel {
    Observable<BaseResponse> rx_otcCoin(POST_OTC_COIN req);
    Observable<BaseResponse> rx_getOtcCoinInfo(GET_USER_COIN_INFO req);
    Observable<BaseResponse> rx_otcTransfer(POST_OTC_TRANSFER_REQ req);
}
