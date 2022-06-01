package com.svv.jys.code.module.myself.login.xieyi.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.login.xieyi.model.XieYiModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;

import rx.Observable;

/**
 * Created by js on 2018/7/2.
 */

public class XieYiModelImpl implements XieYiModel {
    @Override
    public Observable<BaseResponse> rx_protocol(GET_ARTICLE_REQ req) {
        return API_Factory.xieyi(req);
    }
}
