package com.svv.jys.code.module.myself.myasset.countdetail.transfer.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;
import com.svv.jys.code.module.net.req.POST_LT_TRANSFER_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/25.
 */

public interface Transfermodel {
    Observable<BaseResponse> rx_postLttransfer(POST_LT_TRANSFER_REQ req);
    Observable<BaseResponse> rx_getUserCoinInfo(GET_USER_COIN_INFO req);
    Observable<BaseResponse> rx_ltUserCoinInfo(GET_MARKETINFO_REQ req);
}
