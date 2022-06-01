package com.svv.jys.code.module.myself.usercenter.base.pay.addbank.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.pay.addbank.model.AddBankModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.addbank.model.impl.AddBankModelImpl;
import com.svv.jys.code.module.myself.usercenter.base.pay.addbank.view.AddBankView;
import com.svv.jys.code.module.net.req.AddPayReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class AddBankPresenter extends BasePresent<AddBankView,AddBankModel> {
    public AddBankPresenter(){
        model=new AddBankModelImpl();
    }
    public void addPay(String code, final String memo, String password){
        AddPayReq req=new AddPayReq();
        req.code=code;
        req.memo=memo;
        req.password=password;
        model.rx_addpay(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {

                return baseResponse.msg;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(String msg) {
                T.showShort(view.getMContext(),msg);
                view.addSuccese();
            }
        });

    }
}
