package com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.OtcOrderEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.model.OtcOrderModel1;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.model.impl.OtcOrderModelImpl1;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.view.OtcOrderView1;
import com.svv.jys.code.module.net.req.GET_OTC_ORDER_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/9/4.
 */

public class OtcOrderPreserter1 extends BasePresent<OtcOrderView1, OtcOrderModel1> {
    public OtcOrderPreserter1() {
        model = new OtcOrderModelImpl1();
    }

    public void getOtcOrder(final boolean isLoadMore, String status) {
        GET_OTC_ORDER_REQ req = new GET_OTC_ORDER_REQ();
        req.limit = "10";
        req.status = status;
        if (isLoadMore)
            req.offset = view.getRxSize();
        else
            req.offset = "0";
        model.rx_getOtcOrder(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcOrderEntity>() {
            @Override
            public OtcOrderEntity call(BaseResponse baseResponse) {
                OtcOrderEntity entity = JSONObject.parseObject(baseResponse.datas, OtcOrderEntity.class);
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
                view.setOtcOrder(entity,isLoadMore);
            }
        });
    }
}
