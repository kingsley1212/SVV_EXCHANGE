package com.svv.jys.code.module.myself.c2c.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.C2CIndexEntity;
import com.svv.jys.code.common.entity.C2CListEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.c2c.model.C2cModel;
import com.svv.jys.code.module.myself.c2c.model.impl.C2cModelImpl;
import com.svv.jys.code.module.myself.c2c.view.C2cView;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.DO_C2CCANCEL_REQ;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/26.
 */

public class C2cPresenter extends BasePresent<C2cView,C2cModel>{
    public List<String> coins = new ArrayList<>();
    public C2cPresenter(){
        model=new C2cModelImpl();
    }

    public void getIndexData( String coin){
        GET_TRADEACCOUNT_RECORD_REQ req=new GET_TRADEACCOUNT_RECORD_REQ();
        req.coin=coin;
        model.rx_getIndexCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, C2CIndexEntity>() {
            @Override
            public C2CIndexEntity call(BaseResponse baseResponse) {
                return JSONObject.parseObject(baseResponse.datas, C2CIndexEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<C2CIndexEntity>() {
            @Override

            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
                ((Activity)(view.getMContext())).finish();
            }

            @Override
            public void onNext(C2CIndexEntity entity) {
                List<C2CIndexEntity.CoinListBean>   coinBeans = entity.getCoin_list();
                coins.clear();
                for(C2CIndexEntity.CoinListBean bean : coinBeans){
                    coins.add(bean.getName());
                }
                view.setIndexData(entity);
            }
        });
    }

    public void setListData(final boolean isLoadMore){
        GetC2CRecordListReq req=new GetC2CRecordListReq();
        if(isLoadMore) {
            req.setOffset(view.getRvSize());
        }else {
            req.setOffset("0");
        }
        req.setLimit("10");
        model.rx_getC2CList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, C2CListEntity>() {
            @Override
            public C2CListEntity call(BaseResponse baseResponse) {
                return JSONObject.parseObject(baseResponse.datas, C2CListEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<C2CListEntity>() {
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
            public void onNext(C2CListEntity entity) {
                if(isLoadMore){
                    view.loading(entity.getRows());
                }else{
                    view.refresh(entity.getRows());
                }
            }
        });
    }

    public void doCx(String id){
        DO_C2CCANCEL_REQ req = new DO_C2CCANCEL_REQ();
        req.setId(id);
        model.doCancel(req).doOnSubscribe(new Action0() {
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
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
            }

            @Override
            public void onNext(String s) {
                T.showShort(view.getMContext(),s);
                setListData(false);
            }
        });
    }

    public void c2cUp(String coin,String type,String num,String bank,String sec_pwd){
        C2CBuyOrSellReq req = new C2CBuyOrSellReq();
        req.coin = coin;
        req.type = type;
        req.num = num;
        req.bank = bank;
        req.sec_pwd = sec_pwd;
        model.rx_c2cbuyorsell(req).doOnSubscribe(new Action0() {
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
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
            }

            @Override
            public void onNext(String s) {
                T.showShort(view.getMContext(),s);
                setListData(false);
                view.buyorSellSuccese();
            }
        });
    }

}
