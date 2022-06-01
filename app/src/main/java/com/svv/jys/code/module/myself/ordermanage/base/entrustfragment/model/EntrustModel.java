package com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_ENTRUST_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.net.req.POST_UNDO_ENTRUST_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/24.
 */

public interface EntrustModel {
    Observable<BaseResponse> rx_entrust(GET_ENTRUST_REQ req);
    Observable<BaseResponse> rx_undo_entrust(POST_UNDO_ENTRUST_REQ req);
    Observable<BaseResponse> rx_getMarketTitle(POST_KONG_REQ req);
}
