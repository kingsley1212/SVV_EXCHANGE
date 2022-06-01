package com.svv.jys.code.module.home.message_center.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_NOTICE_REQ;

import rx.Observable;

public interface MessageCenterModel {

    Observable<BaseResponse> rx_getMessageCenterData(POST_NOTICE_REQ req);
    Observable<BaseResponse> rx_getArticleData(POST_NOTICE_REQ req);
}
