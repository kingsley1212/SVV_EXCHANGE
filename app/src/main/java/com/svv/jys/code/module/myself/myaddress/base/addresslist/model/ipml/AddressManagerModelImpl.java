package com.svv.jys.code.module.myself.myaddress.base.addresslist.model.ipml;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.model.AddressManagerModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_ADDRESS_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_DELETE_ADDRESS_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/19.
 */

public class AddressManagerModelImpl implements AddressManagerModel{
    @Override
    public Observable<BaseResponse> rx_addresslist(GET_ADDRESS_LIST_REQ req) {
        return API_Factory.API_DoGetAddress(req);
    }

    @Override
    public Observable<BaseResponse> rx_addressdelete(POST_DELETE_ADDRESS_REQ req) {
        return API_Factory.API_PostdelelteAddress(req);
    }
}
