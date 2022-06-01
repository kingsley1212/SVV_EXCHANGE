package com.svv.jys.code.module.myself.inviterecord.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.inviterecord.model.InviteRecordModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.PayMethodReq;

import rx.Observable;

/**
 * Created by js on 2018/6/7.
 */

public class InviteRecordModelImpl implements InviteRecordModel {

    @Override
    public Observable<BaseResponse> rx_getInviteRecord(PayMethodReq req) {
        return API_Factory.API_InviteRecord(req);
    }

    @Override
    public Observable<BaseResponse> rx_getInvite() {
        return API_Factory.API_Invite();
    }

    @Override
    public Observable<BaseResponse> rx_getRewardRecord(PayMethodReq req) {
        return API_Factory.API_RewardRecord(req);
    }
}
