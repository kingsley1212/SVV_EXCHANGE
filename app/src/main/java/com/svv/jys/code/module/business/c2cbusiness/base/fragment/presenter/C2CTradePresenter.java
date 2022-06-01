package com.svv.jys.code.module.business.c2cbusiness.base.fragment.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.C2CCoinInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.c2cbusiness.base.fragment.model.IC2CTradeModel;
import com.svv.jys.code.module.business.c2cbusiness.base.fragment.model.impl.C2CTradeModelImpl;
import com.svv.jys.code.module.business.c2cbusiness.base.fragment.view.IC2CTradeView;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.C2CCoinInfoReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/7/11.
 */

public class C2CTradePresenter extends BasePresent<IC2CTradeView, IC2CTradeModel> {

    public C2CTradePresenter() {
        model = new C2CTradeModelImpl();
    }

    /**
     * 获取币种交易列表
     * @param coin
     */
    public void getc2cCoinInfo(String coin) {
        C2CCoinInfoReq req = new C2CCoinInfoReq();
        req.coin = coin;
        model.rx_c2cCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                C2CCoinInfoEntity>() {
            @Override
            public C2CCoinInfoEntity call(BaseResponse baseResponse) {
                C2CCoinInfoEntity entity = JSONObject.parseObject(baseResponse.datas, C2CCoinInfoEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<C2CCoinInfoEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(C2CCoinInfoEntity s) {
                view.setCoinInfo(s);
            }
        });
    }

    /**
     * 下单
     * @param coin
     * @param type
     * @param num
     * @param bank
     * @param password
     */
    public void buyorsell(String coin, String type, String num, String bank, String password) {
        C2CBuyOrSellReq req = new C2CBuyOrSellReq();
        req.coin = coin;
        req.num = num;
        req.bank = bank;
        req.sec_pwd = password;
        req.type = type;
        model.rx_c2cbuyorsell(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>
                () {
            @Override
            public String call(BaseResponse baseResponse) {

                return baseResponse.datas;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(String s) {
                view.buyorsellsuccese();
            }
        });
    }

}
