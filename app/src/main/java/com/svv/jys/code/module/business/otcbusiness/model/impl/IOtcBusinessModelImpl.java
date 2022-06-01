package com.svv.jys.code.module.business.otcbusiness.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.business.otcbusiness.model.IOtcBusinessModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;

/**
 * Created by js on 2018/8/1.
 */

public class IOtcBusinessModelImpl implements IOtcBusinessModel {
    @Override
    public Observable<BaseResponse> rx_isPublish() {
        return API_Factory.isPublish();
    }
}
