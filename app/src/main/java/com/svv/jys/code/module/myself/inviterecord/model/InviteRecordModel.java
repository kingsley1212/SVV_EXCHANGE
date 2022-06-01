package com.svv.jys.code.module.myself.inviterecord.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.PayMethodReq;

import rx.Observable;

/**
 * Created by js on 2018/6/7.
 */

public interface InviteRecordModel {
    Observable<BaseResponse> rx_getInviteRecord(PayMethodReq req);

    Observable<BaseResponse> rx_getInvite();

    Observable<BaseResponse> rx_getRewardRecord(PayMethodReq req);
}
