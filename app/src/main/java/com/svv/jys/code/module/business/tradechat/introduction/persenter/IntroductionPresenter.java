package com.svv.jys.code.module.business.tradechat.introduction.persenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CoinIntroduceEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.tradechat.introduction.model.IntroductionModel;
import com.svv.jys.code.module.business.tradechat.introduction.model.impl.IntroductionModelImpl;
import com.svv.jys.code.module.business.tradechat.introduction.view.IntroductionView;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/19.
 */

public class IntroductionPresenter extends BasePresent<IntroductionView, IntroductionModel> {
    public IntroductionPresenter() {
        model = new IntroductionModelImpl();
    }
    public void getCoin(String market) {
        GET_MARKET_LIST_REQ req = new GET_MARKET_LIST_REQ();
        req.market = market;
        model.rx_getCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                CoinIntroduceEntity>() {
            @Override
            public CoinIntroduceEntity call(BaseResponse baseResponse) {

                CoinIntroduceEntity entity = JSONObject.parseObject(JSON.parseObject(baseResponse.datas).getString("coin_buy"), CoinIntroduceEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CoinIntroduceEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(CoinIntroduceEntity entity) {
                view.setCoinInfo(entity);
            }
        });
    }

}
