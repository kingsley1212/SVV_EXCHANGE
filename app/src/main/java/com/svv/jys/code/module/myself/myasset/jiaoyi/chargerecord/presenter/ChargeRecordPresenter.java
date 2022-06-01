package com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.MentionRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.model.ChargeRecordModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.model.impl.ChargeRecordModelImpl;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.view.ChargeRecordView;
import com.svv.jys.code.module.net.req.MentionRecordReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ChargeRecordPresenter extends BasePresent<ChargeRecordView,ChargeRecordModel> {
    public ChargeRecordPresenter(){
        model=new ChargeRecordModelImpl();
    }
    public void getMentionRecord(int page,String coin){
        MentionRecordReq req=new MentionRecordReq();
        req.coin=coin;
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_getMentionRecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, MentionRecordEntity>() {
            @Override
            public MentionRecordEntity call(BaseResponse baseResponse) {
                return JSONObject.parseObject(baseResponse.datas,MentionRecordEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<MentionRecordEntity>() {
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
            public void onNext(MentionRecordEntity entity) {
                view.setMentionRecord(entity.getRows());
            }
        });
    }


}
