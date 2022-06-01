package com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.model.SuoCangRecordModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.CunFangRecordReq;

import rx.Observable;

/**
 * Created by js on 2018/8/11.
 */

public class SuoCangRecordModelImpl implements SuoCangRecordModel {
    @Override
    public Observable<BaseResponse> rx_suocangRecord(CunFangRecordReq req) {
        return API_Factory.suocangRecord(req);
    }
}
