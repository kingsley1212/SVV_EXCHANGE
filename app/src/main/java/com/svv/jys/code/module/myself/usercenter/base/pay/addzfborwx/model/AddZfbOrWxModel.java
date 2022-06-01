package com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.AddPayReq;
import com.svv.jys.code.module.net.req.POST_IDENTITY_IMG_REQ;

import rx.Observable;

public interface AddZfbOrWxModel {
    Observable<BaseResponse> rx_upImg(POST_IDENTITY_IMG_REQ req);
    Observable<BaseResponse> rx_addpay(AddPayReq req);
}
