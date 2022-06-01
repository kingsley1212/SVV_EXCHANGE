package com.svv.jys.code.module.myself.area.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/9/8.
 */

public interface AreaModel {
    Observable<BaseResponse> getCountries(POST_KONG_REQ req);
}
