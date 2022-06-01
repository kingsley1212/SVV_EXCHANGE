package com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.model.EntrustModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_ENTRUST_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.net.req.POST_UNDO_ENTRUST_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/24.
 */

public class EntrustModelImpl implements EntrustModel{
    @Override
    public Observable<BaseResponse> rx_entrust(GET_ENTRUST_REQ req) {
        return API_Factory.API_GetMyTradeList(req);
    }

    @Override
    public Observable<BaseResponse> rx_undo_entrust(POST_UNDO_ENTRUST_REQ req) {
        return API_Factory.API_UndoEntrust(req);
    }
    @Override
    public Observable<BaseResponse> rx_getMarketTitle(POST_KONG_REQ req) {
        return API_Factory.API_DoGetMarketArea(req);
    }
}
