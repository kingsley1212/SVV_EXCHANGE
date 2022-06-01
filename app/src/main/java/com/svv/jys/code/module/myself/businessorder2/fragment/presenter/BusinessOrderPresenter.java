package com.svv.jys.code.module.myself.businessorder2.fragment.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.OtcOrderEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.businessorder2.fragment.model.BusinessOrderModel;
import com.svv.jys.code.module.myself.businessorder2.fragment.model.impl.BusinessOrderModelImpl;
import com.svv.jys.code.module.myself.businessorder2.fragment.view.BusinessOrderView;
import com.svv.jys.code.module.net.req.GET_SHOP_ORDER_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/9/4.
 */

public class BusinessOrderPresenter extends BasePresent<BusinessOrderView,BusinessOrderModel> {
    public BusinessOrderPresenter(){
        model=new BusinessOrderModelImpl();
    }

    public void getShopOrder(int page,String stutas){
        GET_SHOP_ORDER_REQ req=new GET_SHOP_ORDER_REQ();
        req.limit="10";
        req.status=stutas;
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_getShopOrder(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

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
            public void onNext(OtcOrderEntity s) {
                view.setBusinessOrder(s);
            }
        });
    }
}

