package com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CoinInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.model.MentionMoneyMode;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.model.impl.MentionMoneyModelImpl;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.view.MentionMoneyView;
import com.svv.jys.code.module.net.req.GET_COININFO_REQ;
import com.svv.jys.code.module.net.req.POST_TRANSFEROUT_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/26.
 */

public class MentionMoneyPresenter extends BasePresent<MentionMoneyView,MentionMoneyMode>{

    public CoinInfoEntity coinInfoEntity;

    public MentionMoneyPresenter(){
        model=new MentionMoneyModelImpl();
    }
    public void transferOut(String coin,String address,String num){
        POST_TRANSFEROUT_REQ req=new POST_TRANSFEROUT_REQ();
        req.address=address;
        req.coin=coin;
        req.num=num;
        model.rx_TransferOut(req).doOnSubscribe(new Action0() {
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
                view.transferoutSuccess();
            }
        });
    }



    public void getCoininfo(String coin){
        GET_COININFO_REQ req=new GET_COININFO_REQ();
        req.coin=coin;
        model.rx_CoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, CoinInfoEntity>() {
            @Override
            public CoinInfoEntity call(BaseResponse baseResponse) {
                CoinInfoEntity entity=JSONObject.parseObject(baseResponse.datas, CoinInfoEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CoinInfoEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
                view.afterGetCoin();
            }

            @Override
            public void onNext(CoinInfoEntity entity) {
                coinInfoEntity = entity;
                view.afterGetCoin();
            }
        });
    }
}
