package com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import rx.Observable;

/**
 * Created by js on 2018/5/26.
 */

public interface ChargeMoneyModel {
    Observable<BaseResponse> rx_setAddress(GET_USER_COIN_INFO req);
}
