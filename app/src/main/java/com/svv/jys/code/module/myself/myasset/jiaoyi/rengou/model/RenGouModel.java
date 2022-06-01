package com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.RenGouRea;

import rx.Observable;

/**
 * Created by js on 2018/10/19.
 */

public interface RenGouModel {
    Observable<BaseResponse> rx_rengou(RenGouRea req);
}
