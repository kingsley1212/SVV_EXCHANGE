package com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.SuoCangReq;

import rx.Observable;

/**
 * Created by js on 2018/8/10.
 */

public interface SuoCangModel {
    Observable<BaseResponse> rx_postSuocang(SuoCangReq req);

    Observable<BaseResponse> rx_postLiXi(SuoCangReq req);
}
