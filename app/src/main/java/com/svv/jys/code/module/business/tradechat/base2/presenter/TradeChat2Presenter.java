package com.svv.jys.code.module.business.tradechat.base2.presenter;

import android.content.Intent;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.module.business.tradechat.base2.model.ITradeChat2Model;
import com.svv.jys.code.module.business.tradechat.base2.model.impl.TradeChat2ModelImpl;
import com.svv.jys.code.module.business.tradechat.base2.adapter.KLineEntity;
import com.svv.jys.code.module.business.tradechat.base2.view.ITradeChat2View;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/6/26.
 */

public class TradeChat2Presenter extends BasePresent<ITradeChat2View, ITradeChat2Model> {
    public MarketListEntity select_ml;

    public TradeChat2Presenter() {
        this.model = new TradeChat2ModelImpl();
    }

    public void getDataFromIntent(Intent intent) {
        this.select_ml = (MarketListEntity) intent.getSerializableExtra("MarketListEntity");
    }


    public void doHandleMarket(List<MarketListEntity> marketListEntities) {
        if (select_ml == null) {
            return;
        }
        Observable.just(marketListEntities).observeOn(Schedulers.io()).map(new Func1<List<MarketListEntity>,
                Boolean>() {
            @Override
            public Boolean call(List<MarketListEntity> entities) {
                try {
                    for (int i = 0; i < entities.size(); i++) {
                        if (entities.get(i).getId().equals(select_ml.getId())) {
                           select_ml.refreshData(entities.get(i));
//                            select_ml = entities.get(i);
//                            select_ml.setNew_price(entities.get(i).getNew_price());
//                            select_ml.setCny_price(entities.get(i).getCny_price());
//                            select_ml.setCny_price(entities.get(i).getChange());
                            return true;
                        }
                    }
                } catch (Exception e) {

                }
                return false;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean bool) {
                if (bool) {
                    view.setMarket();
                }
            }
        });
    }

    public void setmData(List<KLineEntity> kLineEntities) {

    }

}
