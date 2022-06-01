package com.svv.jys.code.module.myself.bank.base.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.BankInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.bank.base.model.IBankManagerModel;
import com.svv.jys.code.module.myself.bank.base.model.impl.BankManagerModelImpl;
import com.svv.jys.code.module.myself.bank.base.view.IBankManagerView;
import com.svv.jys.code.module.net.req.BANKADDRESS_DELETE_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/7/11.
 */

public class BankManagerPresenter extends BasePresent<IBankManagerView, IBankManagerModel> {

    public BankManagerPresenter() {
        model = new BankManagerModelImpl();
    }

    public void banklist() {
        model.rx_banklist().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<BankInfoEntity>>() {
            @Override
            public List<BankInfoEntity> call(BaseResponse baseResponse) {
                List<BankInfoEntity> list = JSON.parseArray(JSON.parseObject(baseResponse.datas).getString("rows"), BankInfoEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<BankInfoEntity>>() {
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
            public void onNext(List<BankInfoEntity> list) {
                view.setbankInfo(list);
            }
        });
    }

    public void deleteBankMes(String id) {
        BANKADDRESS_DELETE_REQ req = new BANKADDRESS_DELETE_REQ();
        req.setId(id);
        model.deleteBankMes(req).doOnSubscribe(new Action0() {
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
                view.deleteBankMesSuccess();
            }
        });
    }
}
