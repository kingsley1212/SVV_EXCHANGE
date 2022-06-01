package com.svv.jys.code.module.fabi.fragment.presenter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;
import com.svv.jys.code.common.entity.OtcCoinEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.fabi.fragment.FabiBuyOrSellFrag;
import com.svv.jys.code.module.fabi.fragment.model.FabiBuyOrSellmodel;
import com.svv.jys.code.module.fabi.fragment.model.impl.FabiBuyOrSellModelImpl;
import com.svv.jys.code.module.fabi.fragment.view.FabiBuyOrSellView;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;


import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/8/1.
 */

public class FabiBuyOrSellPersenter extends BasePresent<FabiBuyOrSellView,FabiBuyOrSellmodel> {
    public AdvSettingEntity settingEntity;
    public FabiBuyOrSellPersenter(){
        model= new FabiBuyOrSellModelImpl();
    }
    public void getOtcCoin(){
        model.rx_otcCoin().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<OtcCoinEntity>>() {
            @Override
            public List<OtcCoinEntity> call(BaseResponse baseResponse) {
                List<OtcCoinEntity> list= JSONObject.parseArray(baseResponse.datas, OtcCoinEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<OtcCoinEntity>>() {
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
            public void onNext(List<OtcCoinEntity> list) {
                view.setOtcCoin(list);
            }
        });
    }

    public void getAdvSetting(){
        model.rx_GetAdvSetting().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, AdvSettingEntity>() {
            @Override
            public AdvSettingEntity call(BaseResponse baseResponse) {
                AdvSettingEntity entity= JSONObject.parseObject(baseResponse.datas,AdvSettingEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AdvSettingEntity>() {
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
            public void onNext(AdvSettingEntity entity) {
                settingEntity = entity;
                view.setData(entity);
            }
        });
    }

    public void getOtcAdv(int page,String trade_type,String money,String country,String pay,String coin){
        GET_OTC_ADV_REQ req=new GET_OTC_ADV_REQ();
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        req.money=money;
        req.trade_type=trade_type;
        req.country=country;
        req.pay=pay;
        req.coin=coin;
        model.rx_otcADv(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcAdvEntity>() {
            @Override
            public OtcAdvEntity call(BaseResponse baseResponse) {
                OtcAdvEntity otcAdvEntity=JSONObject.parseObject(baseResponse.datas,OtcAdvEntity.class);
                return otcAdvEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcAdvEntity>() {
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
            public void onNext(OtcAdvEntity entity) {
                view.setOtcAdv(entity.getRows());
            }
        });
    }

    public void getOtcAdv(int page){
        ((FabiBuyOrSellFrag)view).req.limit="10";
        ((FabiBuyOrSellFrag)view).req.offset = ToolUtils.multiply(((FabiBuyOrSellFrag)view).req.limit, String.valueOf(page - 1));
        model.rx_otcADv(((FabiBuyOrSellFrag)view).req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcAdvEntity>() {
            @Override
            public OtcAdvEntity call(BaseResponse baseResponse) {
                OtcAdvEntity otcAdvEntity=JSONObject.parseObject(baseResponse.datas,OtcAdvEntity.class);
                return otcAdvEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcAdvEntity>() {
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
            public void onNext(OtcAdvEntity entity) {
                view.setOtcAdv(entity.getRows());
            }
        });
    }
    public void getCountries() {
        POST_KONG_REQ req=new POST_KONG_REQ();
        model.getCountries(req)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<CountryEntity>>() {
            @Override
            public List<CountryEntity> call(BaseResponse baseResponse) {
                List<CountryEntity> countryEntities = new ArrayList<>();
                JSONObject jsonObject = JSONObject.parseObject(baseResponse.datas);
                String MapDatas = jsonObject.getString("default");
                String keys = JSONObject.parseObject(MapDatas).getString("keys");
                List<String> strings = JSONArray.parseArray(keys, String.class);
                JSONObject values = JSONObject.parseObject(MapDatas).getJSONObject("values");
                for (String key : strings){
                    JSONArray array = values.getJSONArray(key);
                    for (Object o :array){
                        JSONObject obj = (JSONObject) o;
                        String e = obj.toJSONString();
                        CountryEntity entity =  JSONObject.parseObject(e,CountryEntity.class);
                        countryEntities.add(entity);
                    }
                }
                return countryEntities;
            }
        })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<CountryEntity>>() {
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
            public void onNext(List<CountryEntity> list) {

                view.setCountry(list);
            }
        });
    }
}
