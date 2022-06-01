package com.svv.jys.code.module.myself.myasset.jiaoyi.mentionrecord.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;
import com.svv.jys.code.module.net.req.MentionRecordReq;

import rx.Observable;

public interface MentionRecordModel {
    Observable<BaseResponse> rx_getMentionRecord(MentionRecordReq req);

    Observable<BaseResponse> rx_deleteMention(GET_ARTICLE_REQ req);
}
