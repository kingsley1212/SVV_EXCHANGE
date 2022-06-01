package com.svv.jys.code.module.myself.myasset.otc.otctrading.order.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.OtcOrderEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order.model.OtcOrderModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order.model.impl.OtcOrderModelImpl;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order.view.OtcOrderView;
import com.svv.jys.code.module.net.req.GET_OTC_ORDER_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/11.
 */

public class OtcOrderPreserter extends BasePresent<OtcOrderView,OtcOrderModel>{
    public OtcOrderPreserter(){
        model=new OtcOrderModelImpl();
    }
    public void getOtcOrder(int page, final boolean isRefresh){
        GET_OTC_ORDER_REQ req=new GET_OTC_ORDER_REQ();
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_getOtcOrder(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (!isRefresh){
                    showLoading(view.getMContext());
                }

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcOrderEntity>() {
            @Override
            public OtcOrderEntity call(BaseResponse baseResponse) {
                OtcOrderEntity entity= JSONObject.parseObject(baseResponse.datas,OtcOrderEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcOrderEntity>() {
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
            public void onNext(OtcOrderEntity entity) {
                view.setOtcOrder(entity);
            }
        });
    }
}
