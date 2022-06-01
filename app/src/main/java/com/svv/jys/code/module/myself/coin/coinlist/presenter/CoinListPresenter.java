package com.svv.jys.code.module.myself.coin.coinlist.presenter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CoinAddressEntity;
import com.svv.jys.code.common.entity.CoinInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.coin.coinlist.model.CoinListModel;
import com.svv.jys.code.module.myself.coin.coinlist.model.impl.CoinListModelImpl;
import com.svv.jys.code.module.myself.coin.coinlist.view.CoinListView;
import com.svv.jys.code.module.net.req.GET_COININFO_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by LB on 2018/3/2.
 */

public class CoinListPresenter extends BasePresent<CoinListView, CoinListModel> {
    public List<String> coins = new ArrayList<>();
    public CoinListPresenter() {
        this.model = new CoinListModelImpl();
    }

    public void getAreaCode() {
        model.rx_getCoinList()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<String>>() {
            @Override
            public List<String> call(BaseResponse baseResponse) {
               return JSON.parseArray(baseResponse.datas,String.class);
            }
        })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<String>>() {
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
            public void onNext(List<String> list) {
                coins = list;
                view.setData(list);
            }
        });
    }

    public static String getPinYin(String chines) {
        StringBuffer sb = new StringBuffer();

        sb.setLength(0);
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(nameChar[i]);
            }
        }
        return sb.toString();
    }

    public void setAddress(String coin){
        GET_USER_COIN_INFO req=new GET_USER_COIN_INFO();
        req.coin= coin;
        model.rx_setAddress(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, CoinAddressEntity>() {
            @Override
            public CoinAddressEntity call(BaseResponse baseResponse) {
                CoinAddressEntity entity= JSONObject.parseObject(baseResponse.datas, CoinAddressEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CoinAddressEntity>() {
            @Override

            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
                view.getAddressError();
            }

            @Override
            public void onNext(CoinAddressEntity address) {
            }
        });
    }

    public void getCoininfo(String coin){
        GET_COININFO_REQ req=new GET_COININFO_REQ();
        req.coin=coin;
        model.rx_CoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, CoinInfoEntity>() {
            @Override
            public CoinInfoEntity call(BaseResponse baseResponse) {
                CoinInfoEntity entity=JSONObject.parseObject(baseResponse.datas, CoinInfoEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CoinInfoEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
                view.getCoinFalse();
            }

            @Override
            public void onNext(CoinInfoEntity entity) {
            }
        });
    }
}
