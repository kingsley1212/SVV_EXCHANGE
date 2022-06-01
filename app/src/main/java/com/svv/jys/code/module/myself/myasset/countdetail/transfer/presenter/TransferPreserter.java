package com.svv.jys.code.module.myself.myasset.countdetail.transfer.presenter;


import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.entity.UserCoinInfo;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.countdetail.transfer.model.Transfermodel;
import com.svv.jys.code.module.myself.myasset.countdetail.transfer.model.impl.TransferModelImpl;
import com.svv.jys.code.module.myself.myasset.countdetail.transfer.view.TransferView;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;
import com.svv.jys.code.module.net.req.POST_LT_TRANSFER_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/25.
 */

public class TransferPreserter extends BasePresent<TransferView,Transfermodel> {
    public TransferPreserter() {
        model = new TransferModelImpl();
    }

    public void postTransfer(String coin, String market, String from, String num, String password) {
        POST_LT_TRANSFER_REQ req = new POST_LT_TRANSFER_REQ();
        req.coin = coin;
        req.from = from;
        req.market = market;
        req.num = num;
        req.password = password;
        model.rx_postLttransfer(req).doOnSubscribe(new Action0() {
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
                view.transferSuccess();
            }
        });
    }

    public void getUserCoinInfo(String coin) {
        GET_USER_COIN_INFO req = new GET_USER_COIN_INFO();
        req.coin = coin;
        model.rx_getUserCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, UserCoinInfo>() {
            @Override
            public UserCoinInfo call(BaseResponse baseResponse) {
                UserCoinInfo entity = JSONObject.parseObject(baseResponse.datas, UserCoinInfo.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UserCoinInfo>() {
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
            public void onNext(UserCoinInfo userCoinInfo) {
                view.setUserCoinInfo(userCoinInfo);
            }
        });
    }

    public void getLtuserinfo(String market) {
        GET_MARKETINFO_REQ req = new GET_MARKETINFO_REQ();
        req.market = market;
        model.rx_ltUserCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, LtUserCoinInfoentity>() {
            @Override
            public LtUserCoinInfoentity call(BaseResponse baseResponse) {
                LtUserCoinInfoentity entity = JSONObject.parseObject(baseResponse.datas, LtUserCoinInfoentity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LtUserCoinInfoentity>() {
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
            public void onNext(LtUserCoinInfoentity entity) {
                view.getLtUserinfo(entity);
            }
        });
    }
}
