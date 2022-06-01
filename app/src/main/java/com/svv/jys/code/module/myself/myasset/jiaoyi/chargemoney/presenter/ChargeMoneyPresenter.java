package com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CoinAddressEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.model.ChargeMoneyModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.model.impl.ChargeMoneyModelimpl;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.view.ChargeMoneyView;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/26.
 */

public class ChargeMoneyPresenter extends BasePresent<ChargeMoneyView,ChargeMoneyModel>{
    public ChargeMoneyPresenter(){
        model=new ChargeMoneyModelimpl();
    }
    public CoinAddressEntity coinAddressEntity;
    public void setAddress( String coin){
        GET_USER_COIN_INFO req=new GET_USER_COIN_INFO();
        req.coin= coin;
        model.rx_setAddress(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, CoinAddressEntity>() {
            @Override
            public CoinAddressEntity call(BaseResponse baseResponse) {
                CoinAddressEntity entity= JSONObject.parseObject(baseResponse.datas, CoinAddressEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CoinAddressEntity>() {
            @Override

            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
                view.afterGetAddress();
            }

            @Override
            public void onNext(CoinAddressEntity address) {
                coinAddressEntity = address;
                view.afterGetAddress();
            }
        });
    }

}
