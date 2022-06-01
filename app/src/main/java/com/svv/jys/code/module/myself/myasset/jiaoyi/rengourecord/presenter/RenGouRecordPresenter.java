package com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.presenter;


import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.RenGouRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.model.RenGouRecordModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.model.impl.RenGouRecordModelImpl;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.view.RenGouRecordView;
import com.svv.jys.code.module.net.req.RenGouChexiaoReq;
import com.svv.jys.code.module.net.req.RenGouRecordReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouRecordPresenter extends BasePresent<RenGouRecordView,RenGouRecordModel> {
    public RenGouRecordPresenter(){
        model=new RenGouRecordModelImpl();
    }
    public void getRengouRecord(int page, String type,String coin,final boolean isRefresh){
        RenGouRecordReq req=new RenGouRecordReq();
        req.limit="10";
        req.coin=coin;
        req.type=type;
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_rengourecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (!isRefresh){
                    showLoading(view.getMContext());
                }

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, RenGouRecordEntity>() {
            @Override
            public RenGouRecordEntity call(BaseResponse baseResponse) {
                RenGouRecordEntity entity= JSONObject.parseObject(baseResponse.datas,RenGouRecordEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RenGouRecordEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true,true);
            }

            @Override
            public void onNext(RenGouRecordEntity s) {
                view.setRenGouRecord(s);
            }
        });
    }
    public void chexiao(String id){
        RenGouChexiaoReq req=new RenGouChexiaoReq();
        req.id=id;
        model.rx_chexiao(req).doOnSubscribe(new Action0() {
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
                ToolUtils.doNetErroMsg(view.getMContext(), e, true,true);
            }

            @Override
            public void onNext(String s) {
               if (!ToolUtils.isNull(s)){
                   T_Quick(s);
               }
               view.chexiaoSuccese();
            }
        });
    }

}
