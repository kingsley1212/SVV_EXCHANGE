package com.svv.jys.code.module.myself.apply.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.apply.model.IApplyModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;


public class ApplyModelImpl implements IApplyModel {

    @Override
    public Observable<BaseResponse> rx_apply_info() {
        return API_Factory.API_GetApply();
    }

    @Override
    public Observable<BaseResponse> rx_apply() {
        return API_Factory.API_DoApply();
    }
}
