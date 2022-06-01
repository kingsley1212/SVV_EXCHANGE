package com.svv.jys.code.module.splash.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.splash.model.ISplashModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_APPVERSION_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class SplashModelImpl implements ISplashModel {
    @Override
    public Observable<BaseResponse> rx_getAppVersionData(GET_APPVERSION_REQ req) {
        return API_Factory.API_getAppVersionData(req);
    }
}
