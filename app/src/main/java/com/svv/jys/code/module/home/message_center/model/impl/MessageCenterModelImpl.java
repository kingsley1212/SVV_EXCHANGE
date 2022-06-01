package com.svv.jys.code.module.home.message_center.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.home.message_center.model.MessageCenterModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_NOTICE_REQ;

import rx.Observable;

public class MessageCenterModelImpl implements MessageCenterModel {

    @Override
    public Observable<BaseResponse> rx_getMessageCenterData(POST_NOTICE_REQ req) {
        return API_Factory.API_NOTICE(req);
    }

    @Override
    public Observable<BaseResponse> rx_getArticleData(POST_NOTICE_REQ req) {
        return API_Factory.API_GetArticleClass(req);
    }
}
