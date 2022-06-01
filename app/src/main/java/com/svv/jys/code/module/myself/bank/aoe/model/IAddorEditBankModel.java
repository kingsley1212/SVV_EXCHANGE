package com.svv.jys.code.module.myself.bank.aoe.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.ADD_OR_EDIT_BANKINFO_REQ;

import rx.Observable;

/**
 * Created by lzj on 2018/7/11.
 */

public interface IAddorEditBankModel {

    Observable<BaseResponse> rx_addBank(ADD_OR_EDIT_BANKINFO_REQ req);
}
