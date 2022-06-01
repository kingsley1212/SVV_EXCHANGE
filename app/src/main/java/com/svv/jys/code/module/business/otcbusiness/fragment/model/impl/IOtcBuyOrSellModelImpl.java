package com.svv.jys.code.module.business.otcbusiness.fragment.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.business.otcbusiness.fragment.model.IOtcBuyOrSellModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/8/1.
 */

public class IOtcBuyOrSellModelImpl implements IOtcBuyOrSellModel {
    @Override
    public Observable<BaseResponse> rx_otcCoin() {
        return API_Factory.API_GetotcCoin();
    }

    @Override
    public Observable<BaseResponse> rx_otcADv(GET_OTC_ADV_REQ req) {
        return API_Factory.GetOtcAdv(req);
    }
    @Override
    public Observable<BaseResponse> getCountries(POST_KONG_REQ req) {
        return API_Factory.API_GetCountries(req);
    }
    @Override
    public Observable<BaseResponse> rx_payMethod() {
        return API_Factory.payMethod();
    }
}
