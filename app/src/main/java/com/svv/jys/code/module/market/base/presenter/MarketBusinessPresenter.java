package com.svv.jys.code.module.market.base.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.MarketTitleEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.market.base.model.IMarketBusinessModel;
import com.svv.jys.code.module.market.base.model.impl.MarketModelImpl;
import com.svv.jys.code.module.market.base.view.IMarketBusinessView;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class MarketBusinessPresenter extends BasePresent<IMarketBusinessView, IMarketBusinessModel> {
    public MarketTitleEntity marketEntity;

    public MarketBusinessPresenter() {
        model = new MarketModelImpl();
    }

    public void getMarketTitle() {
        POST_KONG_REQ req = new POST_KONG_REQ();
        model.rx_getMarketTitle(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
               MarketTitleEntity>() {
            @Override
            public MarketTitleEntity call(BaseResponse baseResponse) {
                MarketTitleEntity entity = JSON.parseObject(baseResponse.datas, MarketTitleEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<MarketTitleEntity>() {
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
            public void onNext(MarketTitleEntity entity) {
                marketEntity = entity;
                view.setMarketTitle(entity);
            }
        });
    }

    public void getMarketList(String area) {
        GET_MARKET_LIST_REQ req = new GET_MARKET_LIST_REQ();
        req.area = area;
        model.rx_GetMarketList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<MarketListEntity>>() {
            @Override
            public List<MarketListEntity> call(BaseResponse baseResponse) {
                List<MarketListEntity> list = (List<MarketListEntity>) MarketListEntity.fromJSONListAuto(baseResponse.datas, MarketListEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MarketListEntity>>() {
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
            public void onNext(List<MarketListEntity> marketListEntities) {
                view.setMarketList(marketListEntities);
            }
        });
    }

    public void doHandleMarketList(MarketListEvent marketListEvent) {
        Observable.just(marketListEvent).observeOn(Schedulers.io()).map(new Func1<MarketListEvent,
                List<MarketListEntity>>() {
            @Override
            public List<MarketListEntity> call(MarketListEvent data) {
                try {
                    List<MarketListEntity> marketListEntities = view.getNowShowMarket();
                    MarketListEntity.changeByJSONSocket(data.resData, marketListEntities);
                    return marketListEntities;
                } catch (Exception e) {
                    return new ArrayList<MarketListEntity>();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<MarketListEntity>>() {
            @Override
            public void call(List<MarketListEntity> list) {
                if (list.size() == 0) {
                    return;
                }
                view.setMarket(list);
            }
        });
    }

}
