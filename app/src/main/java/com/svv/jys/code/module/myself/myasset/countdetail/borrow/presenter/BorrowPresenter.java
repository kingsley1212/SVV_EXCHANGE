package com.svv.jys.code.module.myself.myasset.countdetail.borrow.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.MarketInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.countdetail.borrow.model.BorrowModel;
import com.svv.jys.code.module.myself.myasset.countdetail.borrow.model.impl.BorrowModelImpl;
import com.svv.jys.code.module.myself.myasset.countdetail.borrow.view.BorrowView;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.POST_LT_DEBIT_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/25.
 */

public class BorrowPresenter extends BasePresent<BorrowView,BorrowModel>{
    public BorrowPresenter(){
        model=new BorrowModelImpl();
    }
    public void getMarketinfo(String market){
        GET_MARKETINFO_REQ req=new GET_MARKETINFO_REQ();
        req.market=market;
        model.rx_getMarketinfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, MarketInfoEntity>() {
            @Override
            public MarketInfoEntity call(BaseResponse baseResponse) {
                MarketInfoEntity marketInfoEntity= JSONObject.parseObject(baseResponse.datas,MarketInfoEntity.class);
                return marketInfoEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<MarketInfoEntity>() {
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
            public void onNext(MarketInfoEntity marketInfoEntity) {
                view.getMarketinfo(marketInfoEntity);
            }
        });
    }
    public void postToDebit(String market,String coin,String num){
        POST_LT_DEBIT_REQ req=new POST_LT_DEBIT_REQ();
        req.coin=coin;
        req.market=market;
        req.num=num;
        model.rx_postToDebit(req).doOnSubscribe(new Action0() {
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
                view.debitSuccess();
            }
        });
    }
}
