package com.svv.jys.code.module.myself.subscription.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.SubEntity;
import com.svv.jys.code.common.entity.SubListEntity;
import com.svv.jys.code.common.entity.SubscriptionEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.subscription.model.SubscriptionModel;
import com.svv.jys.code.module.myself.subscription.model.impl.SubscriptionModelImpl;
import com.svv.jys.code.module.myself.subscription.view.SubscriptionView;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SubscriptionPresenter extends BasePresent<SubscriptionView,SubscriptionModel> {
    public SubscriptionPresenter(){
        model=new SubscriptionModelImpl();
    }

    public void getSubInfo( ){

        model.rx_getSettingInfo().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, SubscriptionEntity>() {
            @Override
            public SubscriptionEntity call(BaseResponse baseResponse) {
                return JSONObject.parseObject(baseResponse.datas, SubscriptionEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SubscriptionEntity>() {
            @Override

            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
                ((Activity)(view.getMContext())).finish();
            }

            @Override
            public void onNext(SubscriptionEntity entity) {
                view.setSubscriptionInfo(entity);
            }
        });
    }

    public void subscription(String coin,String type,String num,String pwd){

        C2CBuyOrSellReq req=new C2CBuyOrSellReq();
        req.coin=coin;
        req.type=type;
        req.num=num;
        req.sec_pwd=pwd;
        model.rx_subscription(req).doOnSubscribe(new Action0() {
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
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
            }

            @Override
            public void onNext(String s) {
                T.showLong(view.getMContext(),s);
                setListData(false);
                view.succeseSub();
            }
        });
    }


    public void getcoindata(String coin){

        C2CBuyOrSellReq req=new C2CBuyOrSellReq();
        req.coin=coin;
        model.getcoindata(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, SubEntity>() {
            @Override
            public SubEntity call(BaseResponse baseResponse) {
                return JSONObject.parseObject(baseResponse.datas, SubEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SubEntity>() {
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
            public void onNext(SubEntity entity) {
                view.subInfo(entity);

            }
        });
    }


    public void setListData(final boolean isLoadMore){
        GetC2CRecordListReq req=new GetC2CRecordListReq();
        if(isLoadMore) {
            req.setOffset(view.getRvSize());
        }else {
            req.setOffset("0");
        }
        req.setLimit("10");
        model.rx_getC2CList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, SubListEntity>() {
            @Override
            public SubListEntity call(BaseResponse baseResponse) {
                return JSONObject.parseObject(baseResponse.datas, SubListEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SubListEntity>() {
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
            public void onNext(SubListEntity entity) {
                if(isLoadMore){
                    view.loading(entity.getRows());
                }else{
                    view.refresh(entity.getRows());
                }
            }
        });
    }


}
