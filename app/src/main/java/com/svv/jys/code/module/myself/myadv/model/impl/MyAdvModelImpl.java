package com.svv.jys.code.module.myself.myadv.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myadv.model.MyAdvModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.AdvListReq;
import com.svv.jys.code.module.net.req.SetStatusReq;

import rx.Observable;

/**
 * Created by js on 2018/7/27.
 */

public class MyAdvModelImpl implements MyAdvModel {
    @Override
    public Observable<BaseResponse> rx_advList(AdvListReq req) {
        return API_Factory.advList(req);
    }

    @Override
    public Observable<BaseResponse> rx_setStatus(SetStatusReq req) {
        return API_Factory.setStatus(req);
    }
}
