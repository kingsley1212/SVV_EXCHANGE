package com.svv.jys.code.module.business.otcbusiness.fragment.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;
import com.svv.jys.code.common.entity.OtcCoinEntity;
import com.svv.jys.code.common.entity.OtcPayEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.otcbusiness.fragment.model.IOtcBuyOrSellModel;
import com.svv.jys.code.module.business.otcbusiness.fragment.model.impl.IOtcBuyOrSellModelImpl;
import com.svv.jys.code.module.business.otcbusiness.fragment.view.IOtcBuyOrSellView;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/8/1.
 */

public class OtcBuyOrSellPersenter extends BasePresent<IOtcBuyOrSellView, IOtcBuyOrSellModel> {

    public OtcBuyOrSellPersenter() {
        model = new IOtcBuyOrSellModelImpl();
    }

    /**
     * 获取OTC币种
     * @return
     */
    public void getOtcCoin() {
        model.rx_otcCoin().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<OtcCoinEntity>>() {
            @Override
            public List<OtcCoinEntity> call(BaseResponse baseResponse) {
                List<OtcCoinEntity> list = JSONObject.parseArray(baseResponse.datas, OtcCoinEntity.class);
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

    /**
     * 获取OTC广告
     * @return
     */
    public void getOtcAdv(int page, String trade_type, String money, String country, String pay, String coin) {
        GET_OTC_ADV_REQ req = new GET_OTC_ADV_REQ();
        req.limit = "10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        req.money = money;
        req.trade_type = trade_type;
        req.country = country;
        req.pay = pay;
        req.coin = coin;
        model.rx_otcADv(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcAdvEntity>() {
            @Override
            public OtcAdvEntity call(BaseResponse baseResponse) {
                OtcAdvEntity otcAdvEntity = JSONObject.parseObject(baseResponse.datas, OtcAdvEntity.class);
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

    /**
     * 获取OTC广告
     * @return
     */
    public void getCountries() {
        POST_KONG_REQ req = new POST_KONG_REQ();
        model.getCountries(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<CountryEntity>>() {
            @Override
            public List<CountryEntity> call(BaseResponse baseResponse) {
                List<CountryEntity> list = JSONObject.parseArray(baseResponse.datas, CountryEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<CountryEntity>>() {
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
            public void onNext(List<CountryEntity> list) {
                view.setCountry(list);
            }
        });
    }

    public void payMethod(){
        model.rx_payMethod().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcPayEntity>() {
            @Override
            public OtcPayEntity call(BaseResponse baseResponse) {
                OtcPayEntity  entity=JSONObject.parseObject(baseResponse.datas,OtcPayEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcPayEntity>() {
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
            public void onNext(OtcPayEntity entity) {
                view.setPaymMethod(entity.getRows());
            }
        });

    }
}
