package com.svv.jys.code.module.myself.ordermanage.base.detailfragment.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_ORDER_DETAIL_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/24.
 */

public interface DetailModel {
    /**
     * 获取市场数据
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_getMarketTitle(POST_KONG_REQ req);

    /**
     * 获取交易记录
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_entrustDetail(GET_ORDER_DETAIL_REQ req);
}
