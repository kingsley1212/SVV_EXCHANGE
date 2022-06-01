package com.svv.jys.code.module.myself.myasset.otc.otctrading.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;

import rx.Observable;

/**
 * Created by js on 2018/6/8.
 */

public interface OtcTradeModel {
    Observable<BaseResponse> rx_isPublish();
}
