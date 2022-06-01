package com.svv.jys.code.module.business.coinbusiness.base.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.CoinBusinessEntity;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.business.coinbusiness.base.model.ICoinBusinessModel;
import com.svv.jys.code.module.business.coinbusiness.base.model.impl.CoinBusinessModelImpl;
import com.svv.jys.code.module.business.coinbusiness.base.view.ICoinBusinessView;
import com.svv.jys.code.module.net.req.CollectMarketReq;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_TRADEREVOKE_REQ;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/27 0027.
 * V 1.1.1  Update by Lzj  on 2018/8/22 0930.
 */
public class CoinBusinessPresenter extends BasePresent<ICoinBusinessView, ICoinBusinessModel> {

    public static final int P_LIMIT_PRICE = 0;//限价
    public static final int P_MARKET_PRICE = 1;//市价

    public int nowPriceType = P_LIMIT_PRICE;//当前价格模式
    public String sellOrBuyType;// 0为买，1为卖
    public MarketListEntity select_ml;//具体交易对

    public String buy_price,sell_price;//我的价格
    public CoinBusinessEntity entity;
    public String area;
    public HomeAssatEntity assatEntity;
    public String user_id;

    public boolean isSellView() {
        if (!TextUtils.isEmpty(sellOrBuyType) && "1".equals(sellOrBuyType)) {
            return true;
        } else {
            return false;
        }
    }

    public CoinBusinessPresenter() {
        model = new CoinBusinessModelImpl();
    }

    public MarketListEntity getSelect_ml() {
        return select_ml;
    }
    public void setSelect_ml(MarketListEntity select_ml) {
        this.select_ml = select_ml;
    }

    public void getMarketData(String area,String market){
        GET_MARKET_LIST_REQ req = new GET_MARKET_LIST_REQ();
        req.area = area;
        req.market = market;
        model.rx_getMarket(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                CoinBusinessEntity>() {
            @Override
            public CoinBusinessEntity call(BaseResponse baseResponse) {
                return JSON.parseObject(baseResponse.datas, CoinBusinessEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CoinBusinessEntity>() {
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
            public void onNext(CoinBusinessEntity coinBusinessEntity) {
                entity  = coinBusinessEntity;
                setSelect_ml(coinBusinessEntity.getMarket_info());
                view.setBBData(coinBusinessEntity);
            }
        });
    }


    public void Submit(String num, String price, String pwd, final int type, final boolean isCheck){
        POST_COINBUSINESS_SUBMIT_REQ req = new POST_COINBUSINESS_SUBMIT_REQ();
        req.num = num;
        req.price = price;
        req.sec_pwd = pwd;
        req.type = String.valueOf(type);
        req.trade_type = String.valueOf(nowPriceType);
        req.market = entity.getMarket_info().getName();
        model.rx_submit(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.msg;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
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
            public void onNext(String s) {
                view.submitSuccess(type,isCheck);
                T.showShort(view.getMContext(),s);
            }
        });
    }



    public void getCoinBusinessAssetData() {
        model.rx_getCoinBusinessAsset().doOnSubscribe(new Action0() {
            @Override
            public void call() {
//                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                HomeAssatEntity>() {
            @Override
            public HomeAssatEntity call(BaseResponse baseResponse) {
                HomeAssatEntity homeAssatEntity = new HomeAssatEntity();
                homeAssatEntity.fromJSONAuto(baseResponse.datas);
                return homeAssatEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeAssatEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, false, true);
                dismissLoading(view.getMContext());
                view.resetCoinNum();
            }

            @Override
            public void onNext(HomeAssatEntity entity) {
                assatEntity = entity;
                view.setUserData(true);
            }
        });
    }







    public void doTradeRevoke(String id) {
        POST_COINBUSINESS_TRADEREVOKE_REQ req = new POST_COINBUSINESS_TRADEREVOKE_REQ();
        req.id = id;
        model.rx_coinBusinessTradeRevoke(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.msg;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
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
            public void onNext(String s) {
//                view.setTrade(entity);
                T.showShort(view.getMContext(),s);
                view.removeSuccese();
            }
        });
    }


    /**
     * 收藏/取消收藏 市场
     */
    public void collectMarket() {
        CollectMarketReq req = new CollectMarketReq();
        req.market = entity.getMarket_info().getName();
        model.rx_collectMarket(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<String>>() {
            @Override
            public List<String> call(BaseResponse baseResponse) {
                List<String> collectMarket = JSONObject.parseArray(baseResponse.datas, String.class);
                return collectMarket;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, false);
                dismissLoading(view.getMContext());
            }

            @Override
            public void onNext(List<String> list) {
                view.setCollectState(list);
            }
        });
    }



    /**
     * 计算大概的法币价格
     */
    public String setGFbPrice(String price) {
            if (entity!=null){
                String s = ToolUtils.multiplyWithScale(ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE),price, 8);
                String ss = ToolUtils.multiplyWithScale(s, entity
                        .getMarket_info().getUsdt_price(), 2);
                return String.format(view.getMContext().getString(R.string.fbprice_data),ss)+ToolUtils.getCurrency(view.getMContext());
            }

        return "";
    }

    /**
     * 处理市场数据
     *
     * @param marketListEntities
     */
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

}
