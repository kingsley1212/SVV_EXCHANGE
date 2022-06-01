package com.svv.jys.code.module.myself.myasset.otc.otctrading.base.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.model.OtcTradeModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.model.impl.OtcTradeModelImpl;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.view.OtcTradeView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/8.
 */

public class OtcTradePresenter extends BasePresent<OtcTradeView,OtcTradeModel>{
    public OtcTradePresenter(){
        model= new OtcTradeModelImpl();
    }
    public void isPublish(){
        model.rx_isPublish().doOnSubscribe(new Action0() {
            @Override
            public void call() {

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
                ToolUtils.doNetErroMsg(view.getMContext(), e, false);
            }

            @Override
            public void onNext(String s) {
                view.isPublish(s);
            }
        });
    }
}
