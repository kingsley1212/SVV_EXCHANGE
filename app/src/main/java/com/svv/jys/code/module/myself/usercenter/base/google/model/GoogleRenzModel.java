package com.svv.jys.code.module.myself.usercenter.base.google.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GoogleRenzReq;

import rx.Observable;

/**
 * Created by js on 2018/6/22.
 */

public interface GoogleRenzModel {
    Observable<BaseResponse> rx_getGoogleInfo();
    Observable<BaseResponse> rx_setGooglerenz(GoogleRenzReq req);
}
