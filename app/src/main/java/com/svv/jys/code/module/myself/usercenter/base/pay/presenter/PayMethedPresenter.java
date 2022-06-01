package com.svv.jys.code.module.myself.usercenter.base.pay.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.PayMethodEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.pay.model.PayMethedModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.model.ipml.PayMethedModelImpl;
import com.svv.jys.code.module.myself.usercenter.base.pay.view.PayMethedView;
import com.svv.jys.code.module.net.req.BankStatusReq;
import com.svv.jys.code.module.net.req.DeletePayReq;
import com.svv.jys.code.module.net.req.PayMethodReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/16.
 */

public class PayMethedPresenter extends BasePresent<PayMethedView,PayMethedModel>{
    public PayMethedPresenter(){
        model=new PayMethedModelImpl();
    }
    public void getPayMethod(){
        PayMethodReq req=new PayMethodReq();
        model.rx_paymethod(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, PayMethodEntity>() {
            @Override
            public PayMethodEntity call(BaseResponse baseResponse) {
                PayMethodEntity payMethodEntity= JSONObject.parseObject(baseResponse.datas,PayMethodEntity.class);
                return payMethodEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<PayMethodEntity>() {
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
            public void onNext(PayMethodEntity payMethodEntity) {
                view.setPayMethod(payMethodEntity);
            }
        });
    }
    public void deletePay(String id){
        DeletePayReq req=new DeletePayReq();
        req.id=id;
        model.rx_deletepaymethod(req).doOnSubscribe(new Action0() {
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
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(String s) {
                view.deleteSuccese();
            }
        });
    }
    public void setStatus(String id,String state){
        BankStatusReq req=new BankStatusReq();
        req.id=id;
        req.state=state;
        model.rx_bankStatus(req).doOnSubscribe(new Action0() {
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
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(String s) {
                view.setStatusSuccese();
            }
        });
    }
}
