package com.svv.jys.code.module.myself.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.base.model.IMySelfModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_UPDATA_IMG_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class MySelfModelImpl implements IMySelfModel {
    @Override
    public Observable<BaseResponse> rx_getUserInfo() {
        return API_Factory.API_GetUserInfo();
    }

    @Override
    public Observable<BaseResponse> rx_updataImg(POST_UPDATA_IMG_REQ req) {
        return API_Factory.API_updataImg(req);
    }

    @Override
    public Observable<BaseResponse> rx_loginout() {
        return API_Factory.API_DoLoginOut();
    }

    @Override
    public Observable<BaseResponse> rx_IdentityInfo() {
        return API_Factory.API_GetIdentityInfo();
    }
}
