package com.svv.jys.code.module.myself.coinshow.model;

import com.svv.jys.code.common.base.net.BaseResponse;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface ICoinShowModel {
    Observable<BaseResponse> rx_getCoin();
}
