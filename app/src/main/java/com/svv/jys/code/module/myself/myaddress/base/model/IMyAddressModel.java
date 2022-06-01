package com.svv.jys.code.module.myself.myaddress.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_ADD_ADDRESS_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IMyAddressModel {
    Observable<BaseResponse> rx_add_address(POST_ADD_ADDRESS_REQ req);
}
