package com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.AdvEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.model.BuyOrSellModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.model.impl.BuyOrSellModelImpl;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.view.BuyOrSellView;
import com.svv.jys.code.module.net.req.POST_ADV_INFO;
import com.svv.jys.code.module.net.req.POST_OTC_BUY_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/9.
 */

public class BuyOrSellPresenter extends BasePresent<BuyOrSellView, BuyOrSellModel> {
    public BuyOrSellPresenter() {
        model = new BuyOrSellModelImpl();
    }

    public void postOtcAdvBuy(int valueType, String id, String type, String money, String psw, final boolean isCheck) {
        POST_OTC_BUY_REQ req = new POST_OTC_BUY_REQ();
        if(valueType == 0){
            req.currency = money;
        }else {
            req.num = money;
        }
        req.adid = id;
        req.type = type;
        req.sec_pwd=psw;
        model.rx_otcAdvBuy(req).doOnSubscribe(new Action0() {
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
                view.buyOrSellSuccese(s,isCheck);
            }
        });
    }

    public void getAdvInfo(String id, String type) {
        POST_ADV_INFO req = new POST_ADV_INFO();
        req.id = id;
        req.type = type;
        model.rx_gAdvInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, AdvEntity>() {
            @Override
            public AdvEntity call(BaseResponse baseResponse) {
                return JSON.parseObject(baseResponse.datas,AdvEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AdvEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true,true);
            }

            @Override
            public void onNext(AdvEntity entity) {
                view.setInfoData(entity);
            }
        });
    }
}
