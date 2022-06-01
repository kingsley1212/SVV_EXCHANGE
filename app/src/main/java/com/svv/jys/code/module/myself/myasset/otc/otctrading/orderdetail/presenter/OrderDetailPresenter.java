package com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.OrderDetailEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.model.OrderDetailModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.model.inpl.OrderDetailModelImpl;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.view.OrderDetailView;
import com.svv.jys.code.module.net.req.OrderDetailReq;
import com.svv.jys.code.module.net.req.POST_SHENSU_REQ;
import com.svv.jys.code.module.net.req.POST_STASUS_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/11.
 */

public class OrderDetailPresenter extends BasePresent<OrderDetailView, OrderDetailModel> {
    public OrderDetailPresenter() {
        model = new OrderDetailModelImpl();
    }

    public void setStatus(String id, String state) {
        POST_STASUS_REQ req = new POST_STASUS_REQ();
        req.id = id;
        req.state = state;
        model.rx_setStatus(req).doOnSubscribe(new Action0() {
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
                view.setStatusSuccese();
            }
        });
    }

    public void orderShensu(String id, String type, String memo) {
        POST_SHENSU_REQ req = new POST_SHENSU_REQ();
        req.id = id;
        req.type = type;
        req.memo = memo;
        model.rx_orderShensu(req).doOnSubscribe(new Action0() {
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
                view.shensuSuccese();
            }
        });
    }

    public void getOrderDetail(String id) {
        if (ToolUtils.isNull(id)) {
            T.showShort(view.getMContext(),"无法查找该订单");
            return;
        }
        OrderDetailReq req = new OrderDetailReq();
        req.id = id;
        model.rx_orderDetail(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OrderDetailEntity>() {
            @Override
            public OrderDetailEntity call(BaseResponse baseResponse) {
                OrderDetailEntity rowsBean = JSONObject.parseObject(baseResponse.datas, OrderDetailEntity.class);
                return rowsBean;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OrderDetailEntity>() {
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
            public void onNext(OrderDetailEntity s) {
                view.getOrderDetail(s);
            }
        });
    }
}
