package com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.RenGouChexiaoReq;
import com.svv.jys.code.module.net.req.RenGouRecordReq;

import rx.Observable;

/**
 * Created by js on 2018/10/19.
 */

public interface RenGouRecordModel {
    Observable<BaseResponse> rx_rengourecord(RenGouRecordReq req);
    Observable<BaseResponse> rx_chexiao(RenGouChexiaoReq req);
}
