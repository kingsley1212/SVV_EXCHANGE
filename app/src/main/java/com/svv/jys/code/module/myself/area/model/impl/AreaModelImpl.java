package com.svv.jys.code.module.myself.area.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.area.model.AreaModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/9/8.
 */

public class AreaModelImpl implements AreaModel {
    @Override
    public Observable<BaseResponse> getCountries(POST_KONG_REQ req) {
        return API_Factory.API_GetCountries(req);
    }
}
