package com.svv.jys.code.module.myself.welfarerecord.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.WelfareRecordEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.welfarerecord.model.WelfareRecordModel;
import com.svv.jys.code.module.myself.welfarerecord.model.impl.WelfareRecordModelImpl;
import com.svv.jys.code.module.myself.welfarerecord.view.WelfareRecordView;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.WelfareReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class WelfareRecordPresenter extends BasePresent<WelfareRecordView,WelfareRecordModel> {
    public WelfareRecordPresenter(){
        model=new WelfareRecordModelImpl();
    }
    public void getWelfareRecord(int page){
        WelfareReq req=new WelfareReq();
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_getWelfareRecord(req)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, WelfareRecordEntity>() {
            @Override
            public WelfareRecordEntity call(BaseResponse baseResponse) {

                return JSONObject.parseObject(baseResponse.datas,WelfareRecordEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<WelfareRecordEntity>() {
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
            public void onNext(WelfareRecordEntity s) {
                view.setWelfareRecord(s.getRows());
            }
        });
    }
    public void getwelfarereceive(String id){
        AdvInfoReq req=new AdvInfoReq();
       req.id=id;
        model.rx_getWelfareReceive(req)
                .doOnSubscribe(new Action0() {
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
                view.lingquSuccese();
            }
        });
    }
}
