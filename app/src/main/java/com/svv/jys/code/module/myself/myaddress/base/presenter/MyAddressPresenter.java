package com.svv.jys.code.module.myself.myaddress.base.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myaddress.base.model.IMyAddressModel;
import com.svv.jys.code.module.myself.myaddress.base.model.impl.MyAddressModelImpl;
import com.svv.jys.code.module.myself.myaddress.base.view.IMyAddressView;
import com.svv.jys.code.module.net.req.POST_ADD_ADDRESS_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class MyAddressPresenter extends BasePresent<IMyAddressView, IMyAddressModel> {
    public MyAddressPresenter() {
        model = new MyAddressModelImpl();
    }
    public void add_address(String coin,String name,String address){
        POST_ADD_ADDRESS_REQ req=new POST_ADD_ADDRESS_REQ();
        req.coin=coin;
        req.name=name;
        req.address=address;
        model.rx_add_address(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,  String>() {
            @Override
            public  String call(BaseResponse baseResponse) {
                return baseResponse.datas;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber< String>() {
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
            public void onNext( String s) {
                view.successAddAddress();
            }
        });
    }
}
