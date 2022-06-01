package com.svv.jys.code.module.business.c2cbusiness.record.base.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.C2cRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.c2cbusiness.record.base.model.IC2CRecordModel;
import com.svv.jys.code.module.business.c2cbusiness.record.base.model.impl.C2CRecordModelImpl;
import com.svv.jys.code.module.business.c2cbusiness.record.base.view.IC2CRecordView;
import com.svv.jys.code.module.net.req.DO_C2CCANCEL_REQ;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 74099 on 2018/7/11.
 */

public class C2CRecordPresenter extends BasePresent<IC2CRecordView, IC2CRecordModel> {
    public C2CRecordPresenter() {
        model = new C2CRecordModelImpl();
    }

    /***
     * 获取C2C交易记录
     * @param page
     */
    public void getC2CRecordList(int page) {
        GetC2CRecordListReq req = new GetC2CRecordListReq();
        req.setLimit("10");
        req.setOffset(ToolUtils.multiply(req.getLimit(), String.valueOf(page - 1)));
        model.getC2CRecordList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<C2cRecordEntity>>() {
            @Override
            public List<C2cRecordEntity> call(BaseResponse baseResponse) {
                List<C2cRecordEntity> c2cRecordEntityList = JSONObject.parseArray(JSONObject.parseObject(baseResponse.datas).getString("rows"), C2cRecordEntity.class);
                return c2cRecordEntityList;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<C2cRecordEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(List<C2cRecordEntity> s) {
                view.getC2CRecordListSuccess(s);
            }
        });
    }

    /**
     * 删除记录
     * @param id
     */
    public void doCancel(String id) {

        DO_C2CCANCEL_REQ req=new DO_C2CCANCEL_REQ();
        req.setId(id);
        model.doCancel(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.code + "";
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(String s) {
                view.doCancelSuccess();
            }
        });
    }
}
