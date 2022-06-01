package com.svv.jys.code.module.myself.myaddress.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myaddress.base.model.IMyAddressModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_ADD_ADDRESS_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class MyAddressModelImpl implements IMyAddressModel {
    @Override
    public Observable<BaseResponse> rx_add_address(POST_ADD_ADDRESS_REQ req) {
        return API_Factory.API_DoAddAddress(req);
    }
}
