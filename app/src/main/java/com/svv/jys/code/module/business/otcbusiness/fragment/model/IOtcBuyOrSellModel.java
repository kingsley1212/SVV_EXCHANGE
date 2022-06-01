package com.svv.jys.code.module.business.otcbusiness.fragment.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/8/1.
 */

public interface IOtcBuyOrSellModel {
    /**
     * 获取OTC币种
     * @return
     */
    Observable<BaseResponse> rx_otcCoin();
    /**
     * 获取OTC广告
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_otcADv(GET_OTC_ADV_REQ req);
    /**
     * 获取OTC广告
     * @param req
     * @return
     */
    Observable<BaseResponse> getCountries(POST_KONG_REQ req);

    Observable<BaseResponse> rx_payMethod();
}
