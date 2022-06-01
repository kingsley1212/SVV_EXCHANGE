package com.svv.jys.code.module.myself.myasset.jiaoyi.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CunFangSetEntity;
import com.svv.jys.code.common.entity.WalletDataEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.model.TradingAccountModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.model.impl.TradingAccountModelImpl;
import com.svv.jys.code.module.myself.myasset.jiaoyi.view.TradingAccountView;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/26.
 */

public class TradingAccountPresenter extends BasePresent<TradingAccountView, TradingAccountModel> {
    public TradingAccountPresenter() {
        model = new TradingAccountModelImpl();
    }

    public void getUserCoinInfo(String coin, final boolean isLoadMore, int type) {
        GET_TRADEACCOUNT_RECORD_REQ req = new GET_TRADEACCOUNT_RECORD_REQ();
        req.coin = coin;
        if (isLoadMore) {
            req.offset = view.getRySize();
        } else {
            req.offset = "0";
        }
        req.limit = "10";
        Observable<BaseResponse> observable;
        if (type == 0) {
            observable = model.rx_getUserCoinInfo(req);
        } else if (type == 1){
            observable = model.rx_getUserOTCInfo(req);
        }else {
            observable = model.rx_getUserSCInfo(req);
        }


        observable.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, WalletDataEntity>() {
            @Override
            public WalletDataEntity call(BaseResponse baseResponse) {
                return JSONObject.parseObject(baseResponse.datas, WalletDataEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<WalletDataEntity>() {
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
            public void onNext(WalletDataEntity entity) {
                view.putData(entity, isLoadMore);
            }
        });
    }
    public void setNumBankList(){
        model.rx_getNumBankList().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, CunFangSetEntity>() {
            @Override
            public CunFangSetEntity call(BaseResponse baseResponse) {
                CunFangSetEntity entity= JSONObject.parseObject(baseResponse.datas,CunFangSetEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CunFangSetEntity>() {
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
            public void onNext(CunFangSetEntity s) {
                view.setNumBank(s);
            }
        });
    }
}
