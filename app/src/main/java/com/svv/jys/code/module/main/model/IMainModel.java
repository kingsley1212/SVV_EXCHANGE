package com.svv.jys.code.module.main.model;

import com.svv.jys.code.common.base.net.BaseResponse;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface IMainModel {

    Observable<BaseResponse> rx_isCeshi();
}
