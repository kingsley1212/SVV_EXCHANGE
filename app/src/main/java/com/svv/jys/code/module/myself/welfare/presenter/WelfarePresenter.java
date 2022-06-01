package com.svv.jys.code.module.myself.welfare.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.WelfareEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.welfare.model.WelfareModel;
import com.svv.jys.code.module.myself.welfare.model.impl.WelfareModelImpl;
import com.svv.jys.code.module.myself.welfare.view.WelfareView;
import com.svv.jys.code.module.net.req.WelfareReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class WelfarePresenter extends BasePresent<WelfareView,WelfareModel> {
    public WelfarePresenter(){
        model=new WelfareModelImpl();
    }
    public void getWelfare(String status,int page){
        WelfareReq req=new WelfareReq();
        req.status=status;
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_getTangGuo(req)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, WelfareEntity>() {
            @Override
            public WelfareEntity call(BaseResponse baseResponse) {

                return JSONObject.parseObject(baseResponse.datas,WelfareEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<WelfareEntity>() {
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
            public void onNext(WelfareEntity s) {

                view.setWelfare(s.getRows());
            }
        });
    }
}
