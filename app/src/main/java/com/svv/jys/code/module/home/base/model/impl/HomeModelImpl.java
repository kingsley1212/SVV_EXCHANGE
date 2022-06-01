package com.svv.jys.code.module.home.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.home.base.model.IHomeModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_COINBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class HomeModelImpl implements IHomeModel {
    @Override
    public Observable<BaseResponse> rx_getHomeBanner(POST_KONG_REQ req) {
        return API_Factory.API_DoHomeBanner(req);
    }

    @Override
    public Observable<BaseResponse> rx_getNotice(POST_KONG_REQ req) {
        return API_Factory.API_DoGetNotice(req);
    }

    @Override
    public Observable<BaseResponse> rx_getUserCoin(GET_COINBUSINESS_ASSET_REQ req) {
        return API_Factory.API_DoGetUserCoin();
    }

    @Override
    public Observable<BaseResponse> rx_getIndexData() {
        return API_Factory.API_getIndexData();
    }
}
