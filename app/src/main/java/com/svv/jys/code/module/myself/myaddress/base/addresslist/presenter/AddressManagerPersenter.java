package com.svv.jys.code.module.myself.myaddress.base.addresslist.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.AddressEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.model.AddressManagerModel;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.model.ipml.AddressManagerModelImpl;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.view.AddressManagerView;
import com.svv.jys.code.module.net.req.GET_ADDRESS_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_DELETE_ADDRESS_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/19.
 */

public class AddressManagerPersenter extends BasePresent<AddressManagerView,AddressManagerModel>{
    public AddressManagerPersenter(){
        model=new AddressManagerModelImpl();
    }
    public void getAddressList(String coin, final int offset){
        GET_ADDRESS_LIST_REQ req=new GET_ADDRESS_LIST_REQ();
        req.coin=coin;
        req.limit = "10";
        req.offset = String.valueOf(offset);
        model.rx_addresslist(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,  List<AddressEntity>>() {
            @Override
            public  List<AddressEntity> call(BaseResponse baseResponse) {
                List<AddressEntity> list= JSONObject.parseArray(JSON.parseObject(baseResponse.datas).getString("rows"), AddressEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber< List<AddressEntity>>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());

            }

            @Override
            public void onNext( List<AddressEntity> list) {
                if(offset==0){
                    view.setAddress(list);
                }else {
                    view.loadMoreAddress(list);
                }

            }
        });
    }
    public void deleteAddress(String id, final int position){
        POST_DELETE_ADDRESS_REQ req=new POST_DELETE_ADDRESS_REQ();
        req.id=id;
        model.rx_addressdelete(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.datas;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, false);
            }

            @Override
            public void onNext(String success) {
                view.deleteSuccese(position);
            }
        });

    }
}
