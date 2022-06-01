package com.svv.jys.code.module.business.tradechat.transaction.persenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.module.business.tradechat.transaction.model.TransactionModel;
import com.svv.jys.code.module.business.tradechat.transaction.model.impl.TransactionModelImpl;
import com.svv.jys.code.module.business.tradechat.transaction.view.TransactionView;

/**
 * Created by js on 2018/5/19.
 */

public class TransactionPresenter extends BasePresent<TransactionView, TransactionModel> {
    public TransactionPresenter() {
        model = new TransactionModelImpl();
    }



}
