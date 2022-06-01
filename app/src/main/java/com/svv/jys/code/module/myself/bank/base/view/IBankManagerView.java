package com.svv.jys.code.module.myself.bank.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.BankInfoEntity;

import java.util.List;


/**
 * Created by js on 2018/7/11.
 */

public interface IBankManagerView extends BaseView {
    void setbankInfo(List<BankInfoEntity> list);

    void deleteBankMesSuccess();
}
