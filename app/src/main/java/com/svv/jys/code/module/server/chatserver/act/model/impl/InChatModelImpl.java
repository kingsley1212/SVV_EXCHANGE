package com.svv.jys.code.module.server.chatserver.act.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.POST_CHATFILE_REQ;
import com.svv.jys.code.module.server.chatserver.act.model.IInChatModel;

import rx.Observable;

/**
 * Created by lzj on 2018/1/5.
 */

public class InChatModelImpl implements IInChatModel {
    @Override
    public Observable<BaseResponse> rx_postChatFile(POST_CHATFILE_REQ req) {
        return API_Factory.API_DoPostChatFile(req);
    }

    @Override
    public Observable<BaseResponse> rx_getOrderInfo(AdvInfoReq req) {
        return API_Factory.orderInfo(req);
    }
}
