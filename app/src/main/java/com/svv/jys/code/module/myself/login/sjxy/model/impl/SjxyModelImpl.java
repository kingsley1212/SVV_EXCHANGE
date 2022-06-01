package com.svv.jys.code.module.myself.login.sjxy.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.login.sjxy.model.SjxyModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;

/**
 * Created by js on 2018/7/2.
 */

public class SjxyModelImpl implements SjxyModel {
    @Override
    public Observable<BaseResponse> rx_protocol() {
        return API_Factory.API_GETSJXY();
    }
}
