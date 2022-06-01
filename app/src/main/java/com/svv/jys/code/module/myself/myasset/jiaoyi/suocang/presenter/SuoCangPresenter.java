package com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.SuoCangLiXiEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.model.SuoCangModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.model.impl.SuoCangModelImpl;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.view.SuoCangView;
import com.svv.jys.code.module.net.req.SuoCangReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/8/10.
 */

public class SuoCangPresenter extends BasePresent<SuoCangView,SuoCangModel> {
    public SuoCangPresenter(){
        model=new SuoCangModelImpl();
    }

    public void  cunfang(String coin,String rcoin,String revolution){
        SuoCangReq req=new SuoCangReq();
        req.coin=coin;
        req.rcoin = rcoin;
        req.revolution=revolution;
        model.rx_postSuocang(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
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
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(String s) {
                T.showLong(view.getMContext(),view.getMContext().getString(R.string.suoc_suc));
                view.suoCangSucese();
            }
        });
    }

    public void getLiXi(String coin, String rcoin, String time) {
        SuoCangReq req=new SuoCangReq();
        req.coin=coin;
        req.rcoin = rcoin;
        req.revolution=time;
        model.rx_postLiXi(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, SuoCangLiXiEntity>() {
            @Override
            public SuoCangLiXiEntity call(BaseResponse baseResponse) {

                return JSON.parseObject(baseResponse.datas,SuoCangLiXiEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SuoCangLiXiEntity>() {
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
            public void onNext(SuoCangLiXiEntity entity) {
                view.showLiXi(entity);
            }
        });
    }
}
