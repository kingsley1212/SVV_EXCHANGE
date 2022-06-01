package com.svv.jys.code.module.myself.myasset.countdetail.borrow.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.POST_LT_DEBIT_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/25.
 */

public interface BorrowModel {
    Observable<BaseResponse> rx_getMarketinfo(GET_MARKETINFO_REQ req);
    Observable<BaseResponse> rx_postToDebit(POST_LT_DEBIT_REQ req);
}
