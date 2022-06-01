package com.svv.jys.code.module.myself.base.safapsw.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_CHANGEPSW_REQ;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_SAFEPSW_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/21.
 */

public interface SafePswModel {
    Observable<BaseResponse> docode(POST_CODE_REQ req);
    Observable<BaseResponse> rx_setSafePsw(POST_SAFEPSW_REQ req);
    Observable<BaseResponse> rx_changeSafePsw(POST_CHANGEPSW_REQ req);
}
