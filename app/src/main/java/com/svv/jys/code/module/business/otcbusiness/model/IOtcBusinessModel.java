package com.svv.jys.code.module.business.otcbusiness.model;

import com.svv.jys.code.common.base.net.BaseResponse;

import rx.Observable;

/**
 * Created by js on 2018/8/1.
 */

public interface IOtcBusinessModel {
    Observable<BaseResponse> rx_isPublish();
}
