package com.svv.jys.code.module.myself.login.userlogin.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_LOGIN_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public interface IUserLoginModel {
    Observable<BaseResponse> doLogin(POST_LOGIN_REQ req);

    Observable<BaseResponse> rx_getUserInfo();
}
