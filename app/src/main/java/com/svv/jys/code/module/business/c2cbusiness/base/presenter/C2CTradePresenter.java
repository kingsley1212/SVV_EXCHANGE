package com.svv.jys.code.module.business.c2cbusiness.base.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.C2CMarketEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.c2cbusiness.base.model.IC2CTradeModel;
import com.svv.jys.code.module.business.c2cbusiness.base.model.impl.C2CTradeModelImpl;
import com.svv.jys.code.module.business.c2cbusiness.base.view.IC2CTradeView;

import java.util.List;

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

    public void getc2cmarket() {
        model.rx_c2ctrademarkdet().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<C2CMarketEntity>>() {
            @Override
            public List<C2CMarketEntity> call(BaseResponse baseResponse) {
                List<C2CMarketEntity> list = JSONObject.parseArray(baseResponse.datas, C2CMarketEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<C2CMarketEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(List<C2CMarketEntity> s) {
                view.setC2CMarket(s);
            }
        });
    }
}
