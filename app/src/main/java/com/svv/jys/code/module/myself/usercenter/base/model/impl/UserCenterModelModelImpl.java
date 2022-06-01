package com.svv.jys.code.module.myself.usercenter.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.base.model.IUserCenterModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserCenterModelModelImpl implements IUserCenterModel {
    @Override
    public Observable<BaseResponse> rx_getVerify() {
        return API_Factory.API_GetVerify();
    }
    @Override
    public Observable<BaseResponse> rx_IdentityInfo() {
        return API_Factory.API_GetIdentityInfo();
    }
}
