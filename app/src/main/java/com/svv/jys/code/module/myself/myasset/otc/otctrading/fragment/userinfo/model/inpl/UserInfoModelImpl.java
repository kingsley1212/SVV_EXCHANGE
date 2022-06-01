package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.model.inpl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.model.UserInfoModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.BusinessInfoReq;

import rx.Observable;

/**
 * Created by js on 2018/6/20.
 */

public class UserInfoModelImpl implements UserInfoModel {
    @Override
    public Observable<BaseResponse> rx_queryBusinessInfo(BusinessInfoReq req) {
        return API_Factory.qureyBusinessInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_getBusinessList(BusinessInfoReq req) {
        return API_Factory.getBusinessList(req);
    }
}
