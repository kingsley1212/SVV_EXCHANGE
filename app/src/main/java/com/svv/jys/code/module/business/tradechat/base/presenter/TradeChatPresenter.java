package com.svv.jys.code.module.business.tradechat.base.presenter;

import android.content.Intent;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.module.business.tradechat.base.model.ITradeChatModel;
import com.svv.jys.code.module.business.tradechat.base.model.impl.TradeChatModelImpl;
import com.svv.jys.code.module.business.tradechat.base.mychat.MyDataParse;
import com.svv.jys.code.module.business.tradechat.base.view.ITradeChatView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/5/30.
 */

public class TradeChatPresenter extends BasePresent<ITradeChatView, ITradeChatModel> {

    public MyDataParse mData;
    public MarketListEntity select_ml;

    public TradeChatPresenter() {
        this.model = new TradeChatModelImpl();
    }

    public void getDataFromIntent(Intent intent){
        this.select_ml = (MarketListEntity) intent.getSerializableExtra("MarketListEntity");
    }

    public MyDataParse getmData() {
        return mData;
    }

    public void setmData(MyDataParse mData) {
        this.mData = mData;
        view.setKLineData(mData);
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
//                            select_ml.setNew_price(entities.get(i).getNew_price());
//                            select_ml.setCny_price(entities.get(i).getCny_price());
//                            select_ml.setChange(entities.get(i).getChange());
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


//    public void getOffLineData() {
//           /*方便测试，加入假数据*/
//        mData = new DataParse();
//        JSONObject object = null;
//        try {
//            object = new JSONObject(ConstantTest.KLINEURL);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        mData.parseKLine(object);
//        mData.getKLineDatas();
//        view.setKLineData(mData);
//    }

}
