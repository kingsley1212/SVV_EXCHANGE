package com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.model.ChargeRecordModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;
import com.svv.jys.code.module.net.req.MentionRecordReq;

import rx.Observable;

public class ChargeRecordModelImpl implements ChargeRecordModel {
    @Override
    public Observable<BaseResponse> rx_getMentionRecord(MentionRecordReq req) {
        return API_Factory.API_ChargeRecord(req);
    }

    @Override
    public Observable<BaseResponse> rx_deleteMention(GET_ARTICLE_REQ req) {
        return API_Factory.API_DeleteTransfer(req);
    }
}
