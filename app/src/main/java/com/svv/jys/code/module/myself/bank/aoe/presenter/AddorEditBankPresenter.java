package com.svv.jys.code.module.myself.bank.aoe.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.BankInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.bank.aoe.model.IAddorEditBankModel;
import com.svv.jys.code.module.myself.bank.aoe.model.impl.AddorEditBankModelImpl;
import com.svv.jys.code.module.myself.bank.aoe.view.IAddorEditBankView;
import com.svv.jys.code.module.net.req.ADD_OR_EDIT_BANKINFO_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/7/11.
 */

public class AddorEditBankPresenter extends BasePresent<IAddorEditBankView, IAddorEditBankModel> {

    public BankInfoEntity bankInfoEntity;

    public AddorEditBankPresenter() {
        this.model = new AddorEditBankModelImpl();
    }


    public BankInfoEntity getBankInfoEntity() {
        return bankInfoEntity;
    }

    public void setBankInfoEntity(BankInfoEntity bankInfoEntity) {
        this.bankInfoEntity = bankInfoEntity;
    }
    /**
     * 添加银行卡
     */
    public void addBank() {
        ADD_OR_EDIT_BANKINFO_REQ req = new ADD_OR_EDIT_BANKINFO_REQ();
        req.nickname = view.getNickName();
        req.bankuser = view.getBankUser();
        req.bankname = view.getBankName();
        req.bankno = view.getBankNo();
        req.bankaddress = view.getBankAddress();
        if (ToolUtils.isNull(req.nickname) || ToolUtils.isNull(req.bankuser) || ToolUtils.isNull(req.bankname) ||
                ToolUtils.isNull(req.bankno) || ToolUtils.isNull(req.bankaddress)) {
            T_Quick("请填写完整");
            return;
        }
        model.rx_addBank(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                Boolean>() {
            @Override
            public Boolean call(BaseResponse baseResponse) {
                return true;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
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
            public void onNext(Boolean bool) {
                T_Quick("添加成功");
                ((Activity) view.getMContext()).finish();
            }
        });
    }

    /**
     * 编辑银行卡
     */
    public void EditBank() {
        ADD_OR_EDIT_BANKINFO_REQ req = new ADD_OR_EDIT_BANKINFO_REQ();
        if (bankInfoEntity == null) {
            T_Quick("无法获取该银行卡");
            return;
        }
        req.id = bankInfoEntity.getId();
        req.nickname = view.getNickName();
        req.bankuser = view.getBankUser();
        req.bankno = view.getBankNo();
        req.bankaddress = view.getBankAddress();


        model.rx_addBank(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<BankInfoEntity>>() {
            @Override
            public List<BankInfoEntity> call(BaseResponse baseResponse) {
                List<BankInfoEntity> list = JSON.parseArray(baseResponse.datas, BankInfoEntity.class);
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
            }
        });
    }
}
