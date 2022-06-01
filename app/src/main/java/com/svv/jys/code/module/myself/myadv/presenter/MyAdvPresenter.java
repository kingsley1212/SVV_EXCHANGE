package com.svv.jys.code.module.myself.myadv.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.AdvListEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myadv.model.MyAdvModel;
import com.svv.jys.code.module.myself.myadv.model.impl.MyAdvModelImpl;
import com.svv.jys.code.module.myself.myadv.view.MyAdvView;
import com.svv.jys.code.module.net.req.AdvListReq;
import com.svv.jys.code.module.net.req.SetStatusReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/7/27.
 */

public class MyAdvPresenter extends BasePresent<MyAdvView,MyAdvModel> {
    public String coin;
    public MyAdvPresenter(){
        model=new MyAdvModelImpl();
    }

    public void getAdvList(int page,String status){
        AdvListReq req=new AdvListReq();
        req.limit="10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        req.coin = coin;
        req.status = status;
        model.rx_advList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
//                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, AdvListEntity>() {
            @Override
            public AdvListEntity call(BaseResponse baseResponse) {
                return  JSONObject.parseObject(baseResponse.datas,AdvListEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AdvListEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, false);
            }

            @Override
            public void onNext(AdvListEntity entity) {
                view.setAdvList(entity);
            }
        });
    }

    public void setStatus(String id,String status){
        SetStatusReq req=new SetStatusReq();
        req.id=id;
        req.status=status;
        model.rx_setStatus(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse,String>() {
            @Override
            public String call(BaseResponse baseResponse) {
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
                ToolUtils.doNetErroMsg(view.getMContext(), e, false);
            }

            @Override
            public void onNext(String list) {
                view.setStatusSuccese();
            }
        });
    }

}
