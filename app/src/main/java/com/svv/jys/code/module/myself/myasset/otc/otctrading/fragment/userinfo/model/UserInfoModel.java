package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.BusinessInfoReq;

import rx.Observable;

/**
 * Created by js on 2018/6/20.
 */

public interface UserInfoModel {
    Observable<BaseResponse> rx_queryBusinessInfo(BusinessInfoReq req);
    Observable<BaseResponse> rx_getBusinessList(BusinessInfoReq req);
}
