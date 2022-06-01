package com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.AdvInfoEntity;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.model.IOtcFabuSellModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.model.impl.OtcFabuSellModelImpl;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.view.IOtcFabuSellView;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.AdvPulishReq;
import com.svv.jys.code.module.net.req.YiJiaReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/7/24.
 */

public class OtcFabuSellPresenter extends BasePresent<IOtcFabuSellView, IOtcFabuSellModel> {

    public OtcFabuSellPresenter() {
        this.model = new OtcFabuSellModelImpl();
    }

    public void getAdvSetting() {
        model.rx_GetAdvSetting().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, AdvSettingEntity>() {
            @Override
            public AdvSettingEntity call(BaseResponse baseResponse) {
                AdvSettingEntity entity = JSONObject.parseObject(baseResponse.datas, AdvSettingEntity.class);
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
                view.setAdvSetting(entity);
            }
        });
    }

    public void getAdvInfo(String id) {
        AdvInfoReq req=new AdvInfoReq();
        req.id=id;
        model.rx_advInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, AdvInfoEntity>() {
            @Override
            public AdvInfoEntity call(BaseResponse baseResponse) {
                AdvInfoEntity entity = JSONObject.parseObject(baseResponse.datas, AdvInfoEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AdvInfoEntity>() {
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
            public void onNext(AdvInfoEntity entity) {
                view.setAdvInfo(entity);
            }
        });
    }

    public void getyijia(String coin, String num, String code) {
        YiJiaReq req = new YiJiaReq();
        req.code = code;
        req.coin = coin;
        req.num = num;
        model.rx_yijia(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                Log.e("TAG", baseResponse.datas);
                return baseResponse.datas;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                view.setYijia("");
                ToolUtils.doNetErroMsg(view.getMContext(), e, false);
            }

            @Override
            public void onNext(String s) {
                view.setYijia(s);
            }
        });
    }

    public void publish(String id, String type, String country, String currency, String premium, String minamount, String maxamount, String prompt, String num, String message, String payment, String coinname) {
        AdvPulishReq req = new AdvPulishReq();
        req.id = id;
        req.type = type;
        req.currency = currency;
        req.country = country;
        req.premium = premium;
        req.minamount = minamount;
        req.maxamount = maxamount;
        req.prompt = prompt;
        req.num = num;
        req.message = message;
        req.payment = payment;
        req.coin = coinname;
        req.valuetion = "0";
        submitData(req);
    }

    public void publish2(String id, String type, String country, String currency, String price, String minamount, String maxamount, String prompt, String num, String message, String payment, String coinname) {
        AdvPulishReq req = new AdvPulishReq();
        req.id = id;
        req.type = type;
        req.currency = currency;
        req.country = country;
        req.price = price;
        req.minamount = minamount;
        req.maxamount = maxamount;
        req.prompt = prompt;
        req.num = num;
        req.message = message;
        req.payment = payment;
        req.coin = coinname;
        req.valuetion = "1";
        submitData(req);
    }


    public void submitData(AdvPulishReq req) {
        model.rx_pulish(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
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
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(String s) {
                T.showShort(view.getMContext(),s);
                view.publishSuccese();
            }
        });
    }
}
