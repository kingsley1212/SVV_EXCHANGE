package com.svv.jys.code.module.market.slidemenu.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.MarketTitleEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.market.slidemenu.model.SlideMenuModel;
import com.svv.jys.code.module.market.slidemenu.model.impl.SlideMenuModelIpml;
import com.svv.jys.code.module.market.slidemenu.view.SlideMenuView;

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
 * Created by js on 2018/5/17.
 */

public class SlideMenuPersenter extends BasePresent<SlideMenuView, SlideMenuModel> {

    public SlideMenuPersenter() {
        model = new SlideMenuModelIpml();
    }

    public void getMenu() {
        POST_KONG_REQ req = new POST_KONG_REQ();
        model.rx_getMarketTitle(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
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
//                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
//                dismissLoading(view.getMContext());

            }

            @Override
            public void onNext(MarketTitleEntity entity) {
                view.setMenu(entity);
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
