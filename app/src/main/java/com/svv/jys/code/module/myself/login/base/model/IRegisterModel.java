package com.svv.jys.code.module.myself.login.base.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.net.req.POST_REGISTER_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public interface IRegisterModel {
    //发起登录请求
    Observable<BaseResponse> doRegister(POST_REGISTER_REQ req);
    Observable<BaseResponse> docode(POST_CODE_REQ req);
    Observable<BaseResponse> getCountries(POST_KONG_REQ req);
}
