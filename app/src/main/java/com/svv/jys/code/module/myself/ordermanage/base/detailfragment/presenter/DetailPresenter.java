package com.svv.jys.code.module.myself.ordermanage.base.detailfragment.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.EntrustDetailEntity;
import com.svv.jys.code.common.entity.MarketOrederEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.model.DetailModel;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.model.impl.DetailModelImpl;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.view.DetailView;
import com.svv.jys.code.module.net.req.GET_ORDER_DETAIL_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/24.
 */

public class DetailPresenter extends BasePresent<DetailView, DetailModel> {
    public GET_ORDER_DETAIL_REQ req;
    private List<EntrustDetailEntity.RowsBean> datas = new ArrayList<>();

    public DetailPresenter() {
        model = new DetailModelImpl();
    }

    public List<EntrustDetailEntity.RowsBean> getDatas() {
        return datas;
    }

    public void setDatas(List<EntrustDetailEntity.RowsBean> datas) {
        this.datas = datas;
    }

    public void addDatas(List<EntrustDetailEntity.RowsBean> mores) {
        this.datas.addAll(mores);
    }

    /**
     * 获取市场交易对
     */
    public void getMarket() {
        POST_KONG_REQ req = new POST_KONG_REQ();
        model.rx_getMarketTitle(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<MarketOrederEntity>>() {
            @Override
            public List<MarketOrederEntity> call(BaseResponse baseResponse) {
                List<MarketOrederEntity> list = JSONObject.parseArray(baseResponse.datas, MarketOrederEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MarketOrederEntity>>() {
            @Override
            public void onCompleted() {
//                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());

            }

            @Override
            public void onNext(List<MarketOrederEntity> marketTitleEntities) {
                view.setMarket(marketTitleEntities);
            }
        });
    }

    /**
     * 获取订单数据
     */
    public void getEntrustDetail() {
        if (req == null) {
            req = new GET_ORDER_DETAIL_REQ();
            req.limit = "10";
        }
        req.offset = datas.size() + "";
        model.rx_entrustDetail(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                EntrustDetailEntity>() {
            @Override
            public EntrustDetailEntity call(BaseResponse baseResponse) {
                EntrustDetailEntity entity = JSONObject.parseObject(baseResponse.datas, EntrustDetailEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<EntrustDetailEntity>() {
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
            public void onNext(EntrustDetailEntity entity) {
                if (datas.size() == 0) {
                    view.refresh(entity.getRows());
                } else {
                    view.loading(entity.getRows());
                }
            }
        });
    }



}
