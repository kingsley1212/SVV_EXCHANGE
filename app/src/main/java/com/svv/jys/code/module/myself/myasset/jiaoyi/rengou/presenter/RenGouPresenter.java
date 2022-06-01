package com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.presenter;


import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.model.RenGouModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.model.impl.RenGouModelImpl;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.view.RenGouView;
import com.svv.jys.code.module.net.req.RenGouRea;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouPresenter extends BasePresent<RenGouView,RenGouModel> {
    public RenGouPresenter(){
        model=new RenGouModelImpl();
    }
    public void rengou(String type,String coin,String num){
        RenGouRea req=new RenGouRea();
        req.type=type;
        req.coin=coin;
        req.num=num;
        model.rx_rengou(req).doOnSubscribe(new Action0() {
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
                view.rengouSuccese();
            }
        });
    }
}
