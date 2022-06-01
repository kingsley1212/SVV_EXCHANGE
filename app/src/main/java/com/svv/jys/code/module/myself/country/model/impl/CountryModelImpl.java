package com.svv.jys.code.module.myself.country.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.country.model.CountryModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by LB on 2018/3/2.
 */

public class CountryModelImpl implements CountryModel {

    @Override
    public Observable<BaseResponse> rx_getAreaCode(POST_KONG_REQ req) {
        return API_Factory.API_GetCountries(req);
    }
}
