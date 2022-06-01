package com.svv.jys.code.module.myself.login.userlogin.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.login.userlogin.model.IUserLoginModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_LOGIN_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class UserLoginModelImpl implements IUserLoginModel {
    @Override
    public Observable<BaseResponse> doLogin(POST_LOGIN_REQ req) {
        return API_Factory.API_DoLogin(req);
    }

    @Override
    public Observable<BaseResponse> rx_getUserInfo() {
        return API_Factory.API_GetUserInfo();
    }
}
