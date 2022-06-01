package com.svv.jys.code.module.myself.welfare.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.welfare.model.WelfareModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.WelfareReq;

import rx.Observable;

public class WelfareModelImpl implements WelfareModel {
    @Override
    public Observable<BaseResponse> rx_getTangGuo(WelfareReq req) {
        return API_Factory.Api_welfare(req);
    }
}
