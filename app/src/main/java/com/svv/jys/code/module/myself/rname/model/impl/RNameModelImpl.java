package com.svv.jys.code.module.myself.rname.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.rname.model.IRNameModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_R_NAME;

import rx.Observable;


public class RNameModelImpl implements IRNameModel {

    @Override
    public Observable<BaseResponse> rx_setName(POST_R_NAME req) {
        return API_Factory.API_RNAME(req);
    }
}
