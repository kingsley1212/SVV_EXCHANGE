package com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.AdvEntity;

/**
 * Created by js on 2018/6/9.
 */

public interface BuyOrSellView extends BaseView {
    void buyOrSellSuccese(String id,boolean isCheck);

    void setInfoData(AdvEntity entity);
}
