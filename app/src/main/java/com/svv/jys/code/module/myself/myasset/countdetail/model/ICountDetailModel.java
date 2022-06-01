package com.svv.jys.code.module.myself.myasset.countdetail.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_LT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public interface ICountDetailModel {
    Observable<BaseResponse> rx_ltrecord(GET_LT_RECORD_REQ req);
    Observable<BaseResponse> rx_ltUserCoinInfo(GET_MARKETINFO_REQ req);
    Observable<BaseResponse> rx_ltTransferrecord(GET_LT_RECORD_REQ req);
    Observable<BaseResponse> rx_ltBorrowrecord(GET_LT_RECORD_REQ req);
}
