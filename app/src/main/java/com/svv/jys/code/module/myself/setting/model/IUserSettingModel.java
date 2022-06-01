package com.svv.jys.code.module.myself.setting.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_APPVERSION_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IUserSettingModel {
    Observable<BaseResponse> rx_logout();
    Observable<BaseResponse> rx_getAppVersionData(GET_APPVERSION_REQ req);
}
