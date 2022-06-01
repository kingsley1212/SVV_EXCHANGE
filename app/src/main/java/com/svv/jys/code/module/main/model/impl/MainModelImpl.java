package com.svv.jys.code.module.main.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.main.model.IMainModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class MainModelImpl implements IMainModel {
    @Override
    public Observable<BaseResponse> rx_isCeshi() {
        return API_Factory.API_isCeshi();
    }
}
