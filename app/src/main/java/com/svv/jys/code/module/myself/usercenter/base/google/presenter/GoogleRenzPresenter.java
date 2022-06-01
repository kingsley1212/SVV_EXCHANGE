package com.svv.jys.code.module.myself.usercenter.base.google.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.GoogleInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.google.model.GoogleRenzModel;
import com.svv.jys.code.module.myself.usercenter.base.google.model.impl.GoogleRenzModelImpl;
import com.svv.jys.code.module.myself.usercenter.base.google.view.GoogleRenzView;
import com.svv.jys.code.module.net.req.GoogleRenzReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/22.
 */

public class GoogleRenzPresenter extends BasePresent<GoogleRenzView,GoogleRenzModel>{
    public GoogleRenzPresenter(){
        model=new GoogleRenzModelImpl();
    }
    public void getGoogleInfo(){
        model.rx_getGoogleInfo().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, GoogleInfoEntity>() {
            @Override
            public GoogleInfoEntity call(BaseResponse baseResponse) {
                GoogleInfoEntity entity= JSONObject.parseObject(baseResponse.datas,GoogleInfoEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GoogleInfoEntity>() {
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
            public void onNext(GoogleInfoEntity entity) {
                view.setGoogleInfo(entity);
            }
        });
    }
    public void setGoogleRenz(String code){
        GoogleRenzReq req=new GoogleRenzReq();
        req.code=code;
        model.rx_setGooglerenz(req).doOnSubscribe(new Action0() {
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
               view.googleRenzSuccese();
            }
        });
    }
}
