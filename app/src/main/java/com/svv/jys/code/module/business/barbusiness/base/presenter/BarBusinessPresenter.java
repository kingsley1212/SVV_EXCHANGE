package com.svv.jys.code.module.business.barbusiness.base.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.CollectEntity;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.MarketTitleEntity;
import com.svv.jys.code.common.entity.UserCoinInfo;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.barbusiness.base.model.IBarBusinessModel;
import com.svv.jys.code.module.business.barbusiness.base.model.impl.BarBusinessModelImpl;
import com.svv.jys.code.module.business.barbusiness.base.view.IBarBusinessView;
import com.svv.jys.code.module.main.MainAct;
import com.svv.jys.code.module.net.req.CollectMarketReq;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.GET_MY_TRADELIST_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_SUBMIT_MARKETPRICE_REQ;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_TRADEREVOKE_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

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

public class BarBusinessPresenter extends BasePresent<IBarBusinessView, IBarBusinessModel> {

    public static final int P_LIMIT_PRICE = 0;//限价
    public static final int P_MARKET_PRICE = 1;//市价

    public int nowBusinessType = POST_BARBUSINESS_SUBMIT_REQ.P_BUY;//买进/卖出
    public int nowPriceType = P_LIMIT_PRICE;


    public GET_MY_TRADELIST_REQ getMyTradelistReq;//获取我的交易信息

    public MarketTitleEntity select_mt;//交易对
    public MarketListEntity select_ml;//具体交易对
    public CollectEntity collectEntity;//已收藏的交易对实体
    public UserCoinInfo userCoinInfo;
    public LtUserCoinInfoentity ltUserCoinInfoentity;//杠杆交易信息
    public String price;

    public BarBusinessPresenter() {
        model = new BarBusinessModelImpl();
    }

    public MarketTitleEntity getSelect_mt() {
        return select_mt;
    }

    public void setSelect_mt(MarketTitleEntity select_mt) {
        this.select_mt = select_mt;
    }

    public MarketListEntity getSelect_ml() {
        return select_ml;
    }

    /**
     * 选择完市场后，需要配置的资料
     *
     * @param select_ml
     */
    public void setSelect_ml(MarketListEntity select_ml) {
        this.select_ml = select_ml;
        subscribeDept();//订阅交易数据
        view.setMarket();//显示市场数据
        getMyTradeList(true);//获取交易订单
        getLtuserinfo();
        getUserCoinInfo(select_ml.getSell_name());//获取我当前币种的余额
        if (collectEntity != null) {
            view.setCollectMarket(collectEntity.getCollect_market());
        }
    }

    /**
     * 获取交易对头部
     */
    public void gettitle() {
        POST_KONG_REQ req = new POST_KONG_REQ();
        model.rx_getMarketTitle(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<MarketTitleEntity>>() {
            @Override
            public List<MarketTitleEntity> call(BaseResponse baseResponse) {
                List<MarketTitleEntity> list = JSONObject.parseArray(baseResponse.datas, MarketTitleEntity.class);

                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MarketTitleEntity>>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, false, true);
                dismissLoading(view.getMContext());
            }
            @Override
            public void onNext(List<MarketTitleEntity> marketTitleEntities) {
//                view.setTitle(marketTitleEntities);
                if (marketTitleEntities.size() > 0 && select_mt == null) {
                    setSelect_mt(marketTitleEntities.get(0));

                    getMarketList();
                }
            }
        });
    }

    /**
     *
     */
    public void getMarketList() {
        if (select_mt == null) {
            return;
        }
        GET_MARKET_LIST_REQ req = new GET_MARKET_LIST_REQ();
//        req.area = select_mt.getId() + "";
        model.rx_GetMarketList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<MarketListEntity>>() {
            @Override
            public List<MarketListEntity> call(BaseResponse baseResponse) {
                List<MarketListEntity> list = (List<MarketListEntity>) MarketListEntity.fromJSONListAuto(baseResponse
                        .datas, MarketListEntity
                        .class);

                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MarketListEntity>>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, false, true);
                dismissLoading(view.getMContext());
            }

            @Override
            public void onNext(List<MarketListEntity> marketListEntities) {
                if (marketListEntities.size() > 0 && select_ml == null) {
                    setSelect_ml(marketListEntities.get(0));
                    view.setMyPrice(marketListEntities.get(0).getNew_price());
                    subscribeMarket();
                    subscribeDept();
                }
                view.setMarket();
            }
        });
    }


    public void doHandleMarket(List<MarketListEntity> marketListEntities) {
        Observable.just(marketListEntities).observeOn(Schedulers.io()).map(new Func1<List<MarketListEntity>,
                Boolean>() {
            @Override
            public Boolean call(List<MarketListEntity> entities) {
                try {
                    for (int i = 0; i < entities.size(); i++) {
                        if (entities.get(i).getId().equals(select_ml.getId())) {
                            select_ml.setNew_price(entities.get(i).getNew_price());
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


    public void subscribeMarket() {
//        try {
//            ((MainAct) view.getMContext()).mDataService.subscribeAllMarketList();
//        } catch (Exception e) {
//
//        }
    }


    public void subscribeDept() {
        try {
            ((MainAct) view.getMContext()).mDataService.subscribeDept(select_ml.getName());
        } catch (Exception e) {

        }
    }

    /**
     * 开始限价交易
     *
     * @param price
     * @param num
     * @param pw
     */
    public void doPostCoinBusinessByLimit(String price, String num, String pw) {
        if (select_ml == null) {
            return;
        }
        POST_BARBUSINESS_SUBMIT_REQ req = new POST_BARBUSINESS_SUBMIT_REQ();
        req.market = select_ml.getName();
        req.num = num;
        req.price = price;
        req.type = nowBusinessType + "";
        req.pwd = pw;
        model.rx_barBusinessSubmitData(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                Boolean>() {
            @Override
            public Boolean call(BaseResponse baseResponse) {
                T_Quick(baseResponse.msg);
                return true;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
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
            public void onNext(Boolean bool) {
                if (bool) {
                    view.postEntrusSuccese();
                }
            }
        });
    }

    /**
     * 开始市价交易
     *
     * @param price
     * @param num
     * @param pw
     */
    public void doPostCoinBusinessByMarket(String price, String num, String pw) {
        if (select_ml == null) {
            return;
        }
        POST_BARBUSINESS_SUBMIT_MARKETPRICE_REQ req = new POST_BARBUSINESS_SUBMIT_MARKETPRICE_REQ();
        req.market = select_ml.getName();
        req.num = num;
        req.price = price;
        req.type = nowBusinessType + "";
        req.pwd = pw;
        model.rx_barBusinessSubmitDataByMarketPrice(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                Boolean>() {
            @Override
            public Boolean call(BaseResponse baseResponse) {
                T_Quick(baseResponse.msg);
                return true;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
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
            public void onNext(Boolean bool) {
                if (bool) {
                    view.postEntrusSuccese();
                }
            }
        });
    }


    public void setMyTradeStatus(String status) {
        if (getMyTradelistReq == null) {
            getMyTradelistReq = new GET_MY_TRADELIST_REQ();
            getMyTradelistReq.market = select_ml.getName();
            getMyTradelistReq.limit = 10;
            getMyTradelistReq.offset = 0;
        }
        getMyTradelistReq.status = status;
    }

    /**
     * 获取我的委托信息
     * @param isReset
     */
    public void getMyTradeList(boolean isReset) {
        if (select_ml == null) {
            return;
        }
        if (getMyTradelistReq == null) {
            getMyTradelistReq = new GET_MY_TRADELIST_REQ();
        }
        if (isReset) {
            getMyTradelistReq.market = select_ml.getName();
            getMyTradelistReq.limit = 10;
            getMyTradelistReq.offset = 0;
        }
        model.rx_GetMyTradeList(getMyTradelistReq).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<EntrustEntity.RowsBean>>() {
            @Override
            public List<EntrustEntity.RowsBean> call(BaseResponse baseResponse) {
                EntrustEntity entity = new EntrustEntity();
                entity.fromJSONAuto(baseResponse.datas);
                return entity.getRows();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<EntrustEntity.RowsBean>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
//                ToolUtils.doNetErroMsg(view.getMContext(), e, false, true);
            }

            @Override
            public void onNext(List<EntrustEntity.RowsBean> entity) {
                view.setDelegateData(entity);
            }
        });
    }


    /**
     * 移除我的委托
     * @param id
     */
    public void doTradeRevoke(String id) {
        POST_BARBUSINESS_TRADEREVOKE_REQ req = new POST_BARBUSINESS_TRADEREVOKE_REQ();
        req.id = id;
        model.rx_barBusinessTradeRevoke(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                Boolean>() {
            @Override
            public Boolean call(BaseResponse baseResponse) {
                return true;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
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
            public void onNext(Boolean bool) {
//                view.setTrade(entity);
                view.removeSuccese();
            }
        });
    }

    public void getDelegateData() {
        view.isNoData(false);
//        view.setDelegateData(model.getDelegateData());
//        view.loadComplete(true);
    }

    /**
     * 获取用户杠杆分享信息
     */
    public void getLtuserinfo() {
        if(select_ml==null){
            return ;
        }
        GET_MARKETINFO_REQ req = new GET_MARKETINFO_REQ();
        req.market = select_ml.getName();
        model.rx_ltUserCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, LtUserCoinInfoentity>() {
            @Override
            public LtUserCoinInfoentity call(BaseResponse baseResponse) {
                LtUserCoinInfoentity entity = JSONObject.parseObject(baseResponse.datas, LtUserCoinInfoentity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LtUserCoinInfoentity>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
//                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
                view.setLtUserinfo(null);
            }

            @Override
            public void onNext(LtUserCoinInfoentity entity) {
                ltUserCoinInfoentity = entity;
                view.setLtUserinfo(entity);
                setProgessMaxNum();
            }
        });
    }

    /**
     * 获取用户数字资产余额信息
     *
     * @param coin
     */
    public void getUserCoinInfo(String coin) {
        if (coin == null) {
            if (select_ml != null) {
                coin = select_ml.getSell_name();
            } else {
                view.setMaxNum("0");
                return;
            }
        }
        GET_USER_COIN_INFO req = new GET_USER_COIN_INFO();
        req.coin = coin;
        model.rx_getUserCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, UserCoinInfo>() {
            @Override
            public UserCoinInfo call(BaseResponse baseResponse) {
                UserCoinInfo entity = new UserCoinInfo();
                entity.fromJSONAuto(baseResponse.datas);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UserCoinInfo>() {
            @Override

            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
//                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(UserCoinInfo uc) {
                userCoinInfo = uc;
            }
        });
    }

    /**
     * 获取我收藏的市场
     */
    public void collectMarket() {
        CollectMarketReq req = new CollectMarketReq();
        req.market = select_ml.getName();
        model.rx_collectMarket(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<String>>() {
            @Override
            public List<String> call(BaseResponse baseResponse) {
                List<String> collectMarket = JSONObject.parseArray(baseResponse.datas, String.class);
                if (collectEntity != null) {
                    collectEntity.setCollect_market(collectMarket);
                    ACache.get(view.getMContext()).put(ACEConstant.ACE_USERINFO_COLLECTMARKET, collectEntity);
                }
                return collectMarket;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, false, true);
            }

            @Override
            public void onNext(List<String> list) {
                view.setCollectMarket(list);
            }
        });

    }


    /**
     * 设置进度条的最大数量
     */
    public void setProgessMaxNum() {
        if (ltUserCoinInfoentity == null) {
            view.setMaxNum("0.0000");
            return;
        }
        if (nowBusinessType == POST_COINBUSINESS_SUBMIT_REQ.P_BUY) {
            if (select_ml == null) {
                view.setMaxNum("0.0000");
                return;
            }
            if (ToolUtils.String2Double(ltUserCoinInfoentity.getSell_coin()) == 0.00) {
                view.setMaxNum(ltUserCoinInfoentity.getSell_coin());
            } else {
                if (ToolUtils.isNull(price)|| nowPriceType == P_MARKET_PRICE) {
                    view.setMaxNum(ToolUtils.division(ltUserCoinInfoentity.getSell_coin(), select_ml.getNew_price(),
                            4));
                } else {
                    view.setMaxNum(ToolUtils.division(ltUserCoinInfoentity.getSell_coin(), price, 4));
                }
            }
        } else {
            view.setMaxNum(ltUserCoinInfoentity.getBuy_coin() == null ? "0.0000": ltUserCoinInfoentity.getBuy_coin());
        }
    }

    /**
     * 计算大概的法币价格
     */
    public void setGFbPrice() {
        try {
            view.setGFbPriceTx(ToolUtils.doublePoint(ToolUtils.String2Double(ToolUtils.multiply(ToolUtils.division
                    (price, select_ml
                                    .getNew_price(),
                            8), select_ml.getCny_price()))));
        } catch (Exception e) {
            view.setGFbPriceTx(null);
        }
    }

}
