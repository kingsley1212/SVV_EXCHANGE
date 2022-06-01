package com.svv.jys.code.module.myself.apply.model;


import com.svv.jys.code.common.base.net.BaseResponse;

import rx.Observable;

public interface IApplyModel {
    Observable<BaseResponse> rx_apply_info();

    Observable<BaseResponse> rx_apply();
}
