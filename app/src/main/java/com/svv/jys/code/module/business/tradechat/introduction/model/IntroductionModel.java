package com.svv.jys.code.module.business.tradechat.introduction.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/19.
 */

public interface IntroductionModel {
    Observable<BaseResponse> rx_getCoinInfo(GET_MARKET_LIST_REQ req);
}
