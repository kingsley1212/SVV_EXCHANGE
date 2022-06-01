package com.svv.jys.code.module.myself.bank.aoe.view;


import com.svv.jys.code.common.base.mvp.BaseView;

/**
 * Created by lzj on 2018/7/11.
 */

public interface IAddorEditBankView extends BaseView {
    String getNickName();

    String getBankUser();

    String getBankNo();

    String getBankAddress();


    String getBankName();
}
