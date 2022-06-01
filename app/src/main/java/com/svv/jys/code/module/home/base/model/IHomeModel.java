package com.svv.jys.code.module.home.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_COINBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface IHomeModel {
    Observable<BaseResponse> rx_getHomeBanner(POST_KONG_REQ req);
    Observable<BaseResponse> rx_getNotice(POST_KONG_REQ req);
    Observable<BaseResponse> rx_getUserCoin(GET_COINBUSINESS_ASSET_REQ req);

    Observable<BaseResponse> rx_getIndexData();
}
