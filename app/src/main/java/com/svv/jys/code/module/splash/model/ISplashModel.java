package com.svv.jys.code.module.splash.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_APPVERSION_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public interface ISplashModel {
    Observable<BaseResponse> rx_getAppVersionData(GET_APPVERSION_REQ req);
}
