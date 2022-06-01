package com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_ADV_INFO;
import com.svv.jys.code.module.net.req.POST_OTC_BUY_REQ;

import rx.Observable;

/**
 * Created by js on 2018/6/9.
 */

public interface BuyOrSellModel {
    Observable<BaseResponse> rx_otcAdvBuy(POST_OTC_BUY_REQ req);

    Observable<BaseResponse> rx_gAdvInfo(POST_ADV_INFO req);
}
