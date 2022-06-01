package com.svv.jys.code.module.myself.myasset.otc.otctransfer.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.OtcCoinEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.model.OtcTransferModel;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.model.impl.OtcTransferModelImpl;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.view.OtcTransferView;
import com.svv.jys.code.module.net.req.POST_OTC_COIN;
import com.svv.jys.code.module.net.req.POST_OTC_TRANSFER_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/7.
 */

public class OtcTransferPresenter extends BasePresent<OtcTransferView,OtcTransferModel>{
    public OtcCoinEntity entity;
    public String type = "0";
    public OtcTransferPresenter(){
        model=new OtcTransferModelImpl();
    }
    public void getOtcCoin(){
        POST_OTC_COIN req = new POST_OTC_COIN();
        req.type = this.type;
        model.rx_otcCoin(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<OtcCoinEntity>>() {
            @Override
            public List<OtcCoinEntity> call(BaseResponse baseResponse) {
                List<OtcCoinEntity> list=JSONObject.parseArray(baseResponse.datas, OtcCoinEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<OtcCoinEntity>>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true,true);
                ((Activity)(view.getMContext())).finish();
            }

            @Override
            public void onNext(List<OtcCoinEntity> list) {

                view.setOtcCoin(list);
            }
        });
    }

    public void postOtcTransfer(String num,String pwd){
        POST_OTC_TRANSFER_REQ req=new POST_OTC_TRANSFER_REQ();
        req.coin=entity.getName();
        req.pwd=pwd;
        req.from=type;
        if(TextUtils.isEmpty(num)){
              T.showLong(view.getMContext(),view.getMContext().getString(R.string.myassetact_tv2));
              return;
        }
        req.num=num;
        model.rx_otcTransfer(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
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
                T.showShort(view.getMContext(),s);
                view.transferSuccese();
            }
        });

    }
}
