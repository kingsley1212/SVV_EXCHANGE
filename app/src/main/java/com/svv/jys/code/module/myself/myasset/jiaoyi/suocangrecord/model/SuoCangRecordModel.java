package com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.CunFangRecordReq;

import rx.Observable;

/**
 * Created by js on 2018/8/11.
 */

public interface SuoCangRecordModel {
    Observable<BaseResponse> rx_suocangRecord(CunFangRecordReq req);
}
