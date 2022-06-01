package com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.presenter;


import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CunFangRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.model.SuoCangRecordModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.model.impl.SuoCangRecordModelImpl;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.view.SuoCangRecordView;
import com.svv.jys.code.module.net.req.CunFangRecordReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/8/11.
 */

public class SuoCangRecordPresenter extends BasePresent<SuoCangRecordView,SuoCangRecordModel> {
    public SuoCangRecordPresenter(){
        model=new SuoCangRecordModelImpl();
    }
    public void suocangRecord(int page, String coin, final boolean isrefresh){
        CunFangRecordReq req=new CunFangRecordReq();
        req.coin=coin;
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_suocangRecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (!isrefresh){
                    showLoading(view.getMContext());
                }

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, CunFangRecordEntity>() {
            @Override
            public CunFangRecordEntity call(BaseResponse baseResponse) {
                CunFangRecordEntity entity= JSONObject.parseObject(baseResponse.datas,CunFangRecordEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CunFangRecordEntity>() {
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
            public void onNext(CunFangRecordEntity s) {
               view.suocangRecord(s.getRows());
            }
        });
    }
}
