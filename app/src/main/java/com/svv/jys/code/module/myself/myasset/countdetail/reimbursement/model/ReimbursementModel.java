package com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.Post_Reimbursement_Req;

import rx.Observable;

/**
 * Created by js on 2018/5/29.
 */

public interface ReimbursementModel {
    Observable<BaseResponse> rx_postReimburment(Post_Reimbursement_Req req);
}
