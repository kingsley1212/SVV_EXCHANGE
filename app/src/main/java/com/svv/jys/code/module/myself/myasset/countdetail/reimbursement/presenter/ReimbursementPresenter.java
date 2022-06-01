package com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.model.ReimbursementModel;
import com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.model.impl.ReimbursementModelImpl;
import com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.view.ReimbursementView;
import com.svv.jys.code.module.net.req.Post_Reimbursement_Req;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/29.
 */

public class ReimbursementPresenter extends BasePresent<ReimbursementView,ReimbursementModel>{
    public ReimbursementPresenter(){
        model=new ReimbursementModelImpl();
    }
    public void postReimburment(String id,String num,String psw){
        Post_Reimbursement_Req req=new Post_Reimbursement_Req();
        req.id=id;
        req.num=num;
        req.password=psw;
        model.rx_postReimburment(req).doOnSubscribe(new Action0() {
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
                view.reimburmentSuccese();
            }
        });

    }
}
