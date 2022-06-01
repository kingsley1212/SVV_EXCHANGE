package com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.model.inpl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.model.OrderDetailModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.OrderDetailReq;
import com.svv.jys.code.module.net.req.POST_SHENSU_REQ;
import com.svv.jys.code.module.net.req.POST_STASUS_REQ;

import rx.Observable;

/**
 * Created by js on 2018/6/11.
 */

public class OrderDetailModelImpl implements OrderDetailModel {
    @Override
    public Observable<BaseResponse> rx_setStatus(POST_STASUS_REQ req) {
        return API_Factory.SetStatus(req);
    }

    @Override
    public Observable<BaseResponse> rx_orderShensu(POST_SHENSU_REQ req) {
        return API_Factory.orderShensu(req);
    }

    @Override
    public Observable<BaseResponse> rx_orderDetail(OrderDetailReq req) {
        return API_Factory.getOrderDetail(req);
    }
}
