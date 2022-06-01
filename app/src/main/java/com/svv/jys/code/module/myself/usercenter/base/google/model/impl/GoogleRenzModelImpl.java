package com.svv.jys.code.module.myself.usercenter.base.google.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.base.google.model.GoogleRenzModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GoogleRenzReq;

import rx.Observable;

/**
 * Created by js on 2018/6/22.
 */

public class GoogleRenzModelImpl implements GoogleRenzModel {
    @Override
    public Observable<BaseResponse> rx_getGoogleInfo() {
        return API_Factory.getGoogleInfo();
    }

    @Override
    public Observable<BaseResponse> rx_setGooglerenz(GoogleRenzReq req) {
        return API_Factory.setGoogleRenz(req);
    }
}
