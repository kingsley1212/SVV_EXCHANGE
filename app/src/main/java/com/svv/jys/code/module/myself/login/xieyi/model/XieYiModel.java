package com.svv.jys.code.module.myself.login.xieyi.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;

import rx.Observable;

/**
 * Created by js on 2018/7/2.
 */

public interface XieYiModel {
    Observable<BaseResponse> rx_protocol(GET_ARTICLE_REQ req);
}
