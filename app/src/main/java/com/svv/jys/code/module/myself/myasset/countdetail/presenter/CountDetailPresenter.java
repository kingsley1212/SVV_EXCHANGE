package com.svv.jys.code.module.myself.myasset.countdetail.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.LtBorrowRecord;
import com.svv.jys.code.common.entity.LtRecordEntity;
import com.svv.jys.code.common.entity.LtTransferRecord;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.countdetail.model.ICountDetailModel;
import com.svv.jys.code.module.myself.myasset.countdetail.model.impl.CountDetailModelImpl;
import com.svv.jys.code.module.myself.myasset.countdetail.view.ICountDetailView;
import com.svv.jys.code.module.net.req.GET_LT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class CountDetailPresenter extends BasePresent<ICountDetailView, ICountDetailModel> {
    public CountDetailPresenter() {
        model = new CountDetailModelImpl();
    }
    public void getLtRecord(int page,String market){
        GET_LT_RECORD_REQ req=new GET_LT_RECORD_REQ();
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        req.market=market;
        model.rx_ltrecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, LtRecordEntity>() {
            @Override
            public LtRecordEntity call(BaseResponse baseResponse) {
                LtRecordEntity entity=JSONObject.parseObject(baseResponse.datas,LtRecordEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LtRecordEntity>() {
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
            public void onNext(LtRecordEntity entity) {
                view.setLtRecord(entity);
            }
        });
    }
    public void getLtuserinfo(String market){
        GET_MARKETINFO_REQ req=new GET_MARKETINFO_REQ();
        req.market=market;
        model.rx_ltUserCoinInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse,  LtUserCoinInfoentity>() {
            @Override
            public  LtUserCoinInfoentity call(BaseResponse baseResponse) {
                LtUserCoinInfoentity entity= JSONObject.parseObject(baseResponse.datas,LtUserCoinInfoentity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber< LtUserCoinInfoentity>() {
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
            public void onNext( LtUserCoinInfoentity entity) {
                view.getLtUserinfo(entity);
            }
        });
    }
    public void getLtTransferRecord(int page,String market){
        GET_LT_RECORD_REQ req=new GET_LT_RECORD_REQ();
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        req.market=market;
        model.rx_ltTransferrecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, LtTransferRecord>() {
            @Override
            public LtTransferRecord call(BaseResponse baseResponse) {
                LtTransferRecord entity=JSONObject.parseObject(baseResponse.datas,LtTransferRecord.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LtTransferRecord>() {
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
            public void onNext(LtTransferRecord entity) {
                view.setLtTransferRecord(entity);
            }
        });
    }
    public void getBorrowRecord(int page,String market){
        GET_LT_RECORD_REQ req=new GET_LT_RECORD_REQ();
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        req.market=market;
        model.rx_ltBorrowrecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, LtBorrowRecord>() {
            @Override
            public LtBorrowRecord call(BaseResponse baseResponse) {
                LtBorrowRecord entity=JSONObject.parseObject(baseResponse.datas,LtBorrowRecord.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LtBorrowRecord>() {
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
            public void onNext(LtBorrowRecord entity) {
                view.setborrowRecord(entity);
            }
        });
    }
}
