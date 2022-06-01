package com.svv.jys.code.module.myself.ordermanage.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IOrderManagerModel {
    Observable<BaseResponse> rx_getExchange();
}
