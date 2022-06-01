package com.svv.jys.code.module.myself.myaddress.base.addresslist.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_ADDRESS_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_DELETE_ADDRESS_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/19.
 */

public interface AddressManagerModel {
    Observable<BaseResponse> rx_addresslist(GET_ADDRESS_LIST_REQ req);
    Observable<BaseResponse> rx_addressdelete(POST_DELETE_ADDRESS_REQ req);
}
