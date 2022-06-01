package com.svv.jys.code.module.myself.myasset.countdetail.borrow.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.MarketInfoEntity;

/**
 * Created by js on 2018/5/25.
 */

public interface BorrowView extends BaseView {
    void getMarketinfo(MarketInfoEntity marketInfoEntity);
    void debitSuccess();
}
