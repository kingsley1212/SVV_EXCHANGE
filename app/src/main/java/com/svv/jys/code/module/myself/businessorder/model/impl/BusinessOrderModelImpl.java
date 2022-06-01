package com.svv.jys.code.module.myself.businessorder.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.businessorder.model.BusinessOrderModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_SHOP_ORDER_REQ;

import rx.Observable;

/**
 * Created by js on 2018/6/12.
 */

public class BusinessOrderModelImpl implements BusinessOrderModel {
    @Override
    public Observable<BaseResponse> rx_getShopOrder(GET_SHOP_ORDER_REQ req) {
        return API_Factory.GetShopOrder(req);
    }
}
