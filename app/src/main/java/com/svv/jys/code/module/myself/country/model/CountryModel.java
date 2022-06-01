package com.svv.jys.code.module.myself.country.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by LB on 2018/3/2.
 */

public interface CountryModel {

    /*获取国家区号*/
    Observable<BaseResponse> rx_getAreaCode(POST_KONG_REQ req);
}
