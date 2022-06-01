package com.svv.jys.code.module.myself.myaddress.base.coin.persenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CoinEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myaddress.base.coin.model.CoinModel;
import com.svv.jys.code.module.myself.myaddress.base.coin.model.impl.CoinModelimpl;
import com.svv.jys.code.module.myself.myaddress.base.coin.view.CoinView;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/19.
 */

public class CoinPersenter extends BasePresent<CoinView,CoinModel>{
    public CoinPersenter(){
        model=new CoinModelimpl();
    }
    public void getCoin(){
        POST_KONG_REQ req=new POST_KONG_REQ();
        model.rx_coinList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,  List<CoinEntity>>() {
            @Override
            public  List<CoinEntity> call(BaseResponse baseResponse) {

                List<CoinEntity> list= JSONObject.parseArray(JSON.parseObject(baseResponse.datas).getString("rows"), CoinEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber< List<CoinEntity>>() {
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
            public void onNext( List<CoinEntity> entity) {
                view.setCoin(entity);
            }
        });
    }
}
