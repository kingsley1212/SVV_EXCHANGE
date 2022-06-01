package com.svv.jys.code.module.myself.setting.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.setting.model.IUserSettingModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_APPVERSION_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserSettingModelModelImpl implements IUserSettingModel {
    @Override
    public Observable<BaseResponse> rx_logout() {
        return API_Factory.API_DoLoginOut();
    }
    @Override
    public Observable<BaseResponse> rx_getAppVersionData(GET_APPVERSION_REQ req) {
        return API_Factory.API_getAppVersionData(req);
    }
}
