package com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.model.ReimbursementModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.Post_Reimbursement_Req;

import rx.Observable;

/**
 * Created by js on 2018/5/29.
 */

public class ReimbursementModelImpl implements ReimbursementModel {
    @Override
    public Observable<BaseResponse> rx_postReimburment(Post_Reimbursement_Req req) {
        return API_Factory.API_PostReimbursement(req);
    }
}
