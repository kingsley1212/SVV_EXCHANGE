package com.svv.jys.code.module.myself.myasset.otc.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.OtcCoinInfoEntity;
import com.svv.jys.code.common.entity.OtcRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.model.OtcDetailModel;
import com.svv.jys.code.module.myself.myasset.otc.model.impl.OtcDetailModelimpl;
import com.svv.jys.code.module.myself.myasset.otc.view.OtcDetailView;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/7.
 */

public class OtcDetailPersenter extends BasePresent<OtcDetailView,OtcDetailModel>{
    public OtcDetailPersenter(){
        model=new OtcDetailModelimpl();
    }
    public void getOtcUserInfo(String coin){
        GET_USER_COIN_INFO req=new GET_USER_COIN_INFO();
        req.coin=coin;
        model.rx_getOtcCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcCoinInfoEntity>() {
            @Override
            public OtcCoinInfoEntity call(BaseResponse baseResponse) {
                OtcCoinInfoEntity entity= JSONObject.parseObject(baseResponse.datas, OtcCoinInfoEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcCoinInfoEntity>() {
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
            public void onNext(OtcCoinInfoEntity entity) {
                view.setOtcInfo(entity);
            }
        });
    }
    public void getOtcList(int page, String coin, final boolean isfresh){
        GET_TRADEACCOUNT_RECORD_REQ req=new GET_TRADEACCOUNT_RECORD_REQ();
        req.coin=coin;
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_getOtcCoinList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (!isfresh){
                    showLoading(view.getMContext());
                }

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcRecordEntity>() {
            @Override
            public OtcRecordEntity call(BaseResponse baseResponse) {
                OtcRecordEntity entity= JSONObject.parseObject(baseResponse.datas, OtcRecordEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcRecordEntity>() {
            @Override

            public void onCompleted() {
//                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
            }

            @Override
            public void onNext(OtcRecordEntity entity) {
                view.setRecordList(entity);
            }
        });
    }
}
