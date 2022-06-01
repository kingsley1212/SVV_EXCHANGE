package com.svv.jys.code.module.business.tradechat.base3.presenter;

import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.fujianlian.klinechart.DataHelper;
import com.github.fujianlian.klinechart.KLineEntity;
import com.github.fujianlian.klinechart.base.IDateTimeFormatter;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.TimeDayUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.tradechat.base3.model.ITradeChat3Model;
import com.svv.jys.code.module.business.tradechat.base3.model.impl.TradeChat3ModelImpl;
import com.svv.jys.code.module.business.tradechat.base3.util.GET_OLDKLINEDATA_REQ;
import com.svv.jys.code.module.business.tradechat.base3.util.GetOldKLineDataResponse;
import com.svv.jys.code.module.business.tradechat.base3.view.ITradeChat3View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/6/26.
 */

public class TradeChat3Presenter extends BasePresent<ITradeChat3View, ITradeChat3Model> implements IDateTimeFormatter {
    public MarketListEntity select_ml;
    public List<KLineEntity> kLineDatas = new ArrayList<>();
    public String symbol_title;
    public String resolution;
    public static String klineFormat = "MM/dd HH:mm";
    public SimpleDateFormat format = new SimpleDateFormat(klineFormat);
    public boolean isWeek;
    //    KLineEntity weekLastEntity;
    float weekLastVolume;

    public Map<String, Integer> map = new HashMap();

    public TradeChat3Presenter() {
        this.model = new TradeChat3ModelImpl();

        map.put("1M", 1);
        map.put("5M", 5);
        map.put("15M", 15);
        map.put("1H", 60);
        map.put("1D", 1440);
    }

    public void getDataFromIntent(Intent intent) {
        this.select_ml = (MarketListEntity) intent.getSerializableExtra("MarketListEntity");
        symbol_title = select_ml.getName().replace("_", "/").toUpperCase();
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


    public void doHandleKline(String resData) {
        if (select_ml == null || ToolUtils.isNull(resolution)) {
            return;
        }
        Observable.just(resData).observeOn(Schedulers.io()).map(new Func1<String,
                Object[]>() {
            @Override
            public Object[] call(String datas) {
                Object[] objects = {"-1", ""};
                try {
                    JSONObject trade_json = JSONObject.parseObject(datas).getJSONObject("trade_json");
                    JSONObject aim = trade_json.getJSONObject(select_ml.getName());
                    JSONArray en = aim.getJSONArray(resolution);
                    Log.i("tradeview", aim.toJSONString());
                    String Timestamp = ToolUtils.timeStamp2String(en.getString(0), klineFormat);
                    KLineEntity lastKl = null;
                    if (kLineDatas != null && kLineDatas.size() > 0) {
                        lastKl = kLineDatas.get(kLineDatas.size() - 1);
                    }
                    if (!isWeek) {//不是周线
                        if (lastKl != null && lastKl.Date.equals(Timestamp)) {//通过时间戳判断是否是同一跟柱子
//                        lastKl.Open = ToolUtils.String2Float(en.getString(1));
                            lastKl.Timestamp = en.getLongValue(0);
                            lastKl.Date = ToolUtils.timeStamp2String(en.getString(0), klineFormat);
                            lastKl.High = ToolUtils.String2Float(en.getString(2));
                            lastKl.Low = ToolUtils.String2Float(en.getString(3));
                            lastKl.Close = ToolUtils.String2Float(en.getString(4));
                            lastKl.Volume = ToolUtils.String2Float(en.getString(5));
                            objects[0] = "0";//最后一根处理方式 刷新
                            objects[1] = lastKl;
                        } else {//新增一根柱子
                            KLineEntity entity = new KLineEntity();
                            entity.Timestamp = en.getLongValue(0);
                            entity.Date = ToolUtils.timeStamp2String(en.getString(0), klineFormat);
                            entity.Open = ToolUtils.String2Float(en.getString(1));
                            entity.High = ToolUtils.String2Float(en.getString(2));
                            entity.Low = ToolUtils.String2Float(en.getString(3));
                            entity.Close = ToolUtils.String2Float(en.getString(4));
                            entity.Volume = ToolUtils.String2Float(en.getString(5));
                            if (lastKl != null) {
                                entity.Open = lastKl.Close;
                            }
                            kLineDatas.add(entity);
                            objects[0] = "1";//最后一根处理方式 新增
                            objects[1] = entity;
                        }
                    } else {
//                        Date date = new Date(lastKl.getTimestamp());//获取当前最后一根时间
//                        Date date1 = new Date(en.getLongValue(0));//socket最后一个实体的时间
                        if (lastKl != null && !(TimeDayUtils.getWeekDay(lastKl.getTimestamp()) == 1 && TimeDayUtils.getWeekDay(en.getLongValue(0)) == 2)) {
//                        lastKl.Open = ToolUtils.String2Float(en.getString(1));
                            lastKl.Timestamp = en.getLongValue(0);
                            lastKl.Date = ToolUtils.timeStamp2String(en.getString(0), "yyyy-MM-dd");

                            if (lastKl.getHighPrice() < ToolUtils.String2Float(en.getString(2))) {//比较最高价
                                lastKl.High = ToolUtils.String2Float(en.getString(2));
                            }

                            if (lastKl.getLowPrice() > ToolUtils.String2Float(en.getString(3))) {//比较最低价
                                lastKl.Low = ToolUtils.String2Float(en.getString(3));
                            }

                            lastKl.Close = ToolUtils.String2Float(en.getString(4));

                            float newVolume = lastKl.getVolume() - weekLastVolume + ToolUtils.String2Float(en.getString(5));
                            lastKl.Volume = newVolume;
                            objects[0] = "0";
                            objects[1] = lastKl;
                        } else {
                            KLineEntity entity = new KLineEntity();
                            entity.Timestamp = en.getLongValue(0);
                            entity.Date = ToolUtils.timeStamp2String(en.getString(0), "yyyy-MM-dd");
                            entity.Open = ToolUtils.String2Float(en.getString(1));
                            entity.High = ToolUtils.String2Float(en.getString(2));
                            entity.Low = ToolUtils.String2Float(en.getString(3));
                            entity.Close = ToolUtils.String2Float(en.getString(4));
                            entity.Volume = ToolUtils.String2Float(en.getString(5));
                            weekLastVolume = ToolUtils.String2Float(en.getString(5));
                            if (lastKl != null) {
                                entity.Open = lastKl.Close;
                            }
                            kLineDatas.add(entity);
                            objects[0] = "1";
                            objects[1] = entity;
                        }
                    }

                    return objects;
                } catch (Exception e) {
                    objects[0] = "-1";
                }
                return objects;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object[]>() {
            @Override
            public void call(Object[] objects) {
                if (!objects[0].equals("-1")) {
                    view.klineDatanotify((String) objects[0], (KLineEntity) objects[1]);
                }
            }
        });
    }


    public void get1MinLineData() {
        getOldKLineData(1, 500);
    }

    public void get5MinLineData() {
        getOldKLineData(5, 500);
    }


    public void get15MinLineData() {
        getOldKLineData(15, 500);
    }

    public void get1HLineData() {
        getOldKLineData(60, 500);
    }

    public void get1DLineData() {
        getOldKLineData(1440, 500);
    }

    public void get1WeekLineData() {
        getWeekKLineData(1440, 3500);

    }

    public void getOldKLineData(int resolution, int resnum) {
        isWeek = false;
        GET_OLDKLINEDATA_REQ req = new GET_OLDKLINEDATA_REQ();
        req.symbol = select_ml.getName();
        req.resolution = resolution + "";
        this.resolution = req.resolution;
        req.to = (int) (System.currentTimeMillis() / 1000) + "";
        req.from = ((int) (System.currentTimeMillis() / 1000) - resolution * 60 * resnum) + "";
        model.rx_getoldklinedata(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                view.klineshowloading();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<GetOldKLineDataResponse,
                List<KLineEntity>>() {
            @Override
            public List<KLineEntity> call(GetOldKLineDataResponse baseResponse) {
                List<KLineEntity> datas = baseResponse.rows;
//                List<KLineEntity> datas = DataRequest.getALL(view.getMContext()).subList(0, 500);
                DataHelper.calculate(datas);
                return datas;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<KLineEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(List<KLineEntity> s) {
                kLineDatas = s;
                view.setKlineData(kLineDatas);
            }
        });


    }


    public void getWeekKLineData(int resolution, int resnum) {
        isWeek = true;
        GET_OLDKLINEDATA_REQ req = new GET_OLDKLINEDATA_REQ();
        req.symbol = select_ml.getName();
        req.resolution = resolution + "";
        this.resolution = req.resolution;
        req.to = (int) (System.currentTimeMillis() / 1000) + "";
        req.from = ((int) (System.currentTimeMillis() / 1000) - resolution * 60 * resnum) + "";
        model.rx_getoldklinedata(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                view.klineshowloading();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<GetOldKLineDataResponse,
                List<KLineEntity>>() {
            @Override
            public List<KLineEntity> call(GetOldKLineDataResponse baseResponse) {
                List<KLineEntity> datas = baseResponse.rows;
                weekLastVolume = datas.get(datas.size() - 1).getVolume();//获取最新一天的交易量
                List<KLineEntity> dealWithData = DataHelper.dayChangeWeek(datas);//7天数据合并
                DataHelper.calculate(dealWithData);
                return dealWithData;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<KLineEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(List<KLineEntity> s) {
                kLineDatas = s;
                view.setKlineData(kLineDatas);
            }
        });


    }

    public void setmData(List<KLineEntity> kLineEntities) {

    }


    @Override
    public String format(Date date) {
        if (date != null) {
            return format.format(date);
        } else {
            return "";
        }
    }
}
