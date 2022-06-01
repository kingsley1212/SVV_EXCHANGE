package com.svv.jys.code.module.myself.usercenter.base.pay.addpay.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.OtcPayEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.model.AddPayModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.model.impl.AddPayModelimpl;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.view.AddPayView;
import com.svv.jys.code.module.net.req.AddPayReq;
import com.svv.jys.code.module.net.req.POST_VERIFY_PAYMENT;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/16.
 */

public class AddPayPresenter extends BasePresent<AddPayView,AddPayModel> {
    public AddPayPresenter(){
        model= new AddPayModelimpl();
    }
    public void addPay(String id,String code,String memo,String password){
        AddPayReq req=new AddPayReq();
        req.id=id;
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
            public void onNext(String payMethodEntity) {
                view.addSuccese();
            }
        });

    }
    public void payMethod(){
        model.rx_payMethod().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcPayEntity>() {
            @Override
            public OtcPayEntity call(BaseResponse baseResponse) {
                OtcPayEntity  entity=JSONObject.parseObject(baseResponse.datas,OtcPayEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcPayEntity>() {
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
            public void onNext(OtcPayEntity entity) {
                view.setPaymMethod(entity);
            }
        });

    }

    public void verify(final String code){
        POST_VERIFY_PAYMENT req = new POST_VERIFY_PAYMENT();
        req.type = "0";
        req.code = code;
        model.rx_VerifyPayMethod(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcPayEntity.RowsBean>() {
            @Override
            public OtcPayEntity.RowsBean call(BaseResponse baseResponse) {
                return JSONObject.parseObject(baseResponse.datas,OtcPayEntity.RowsBean.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcPayEntity.RowsBean>() {
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
            public void onNext(OtcPayEntity.RowsBean payMethodEntity) {
                view.verifySuccess(payMethodEntity);
            }
        });

    }

}
