package com.svv.jys.code.module.myself.bank.aoe.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.bank.aoe.model.IAddorEditBankModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.ADD_OR_EDIT_BANKINFO_REQ;

import rx.Observable;

/**
 * Created by lzj on 2018/7/11.
 */

public class AddorEditBankModelImpl implements IAddorEditBankModel {
    @Override
    public Observable<BaseResponse> rx_addBank(ADD_OR_EDIT_BANKINFO_REQ req) {
        return API_Factory.addbank(req);
    }
}
