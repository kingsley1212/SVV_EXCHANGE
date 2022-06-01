package com.svv.jys.code.module.business.c2cbusiness.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.C2CMarketEntity;

import java.util.List;

/**
 * Created by js on 2018/7/11.
 */

public interface IC2CTradeView extends BaseView {
    /**
     * 显示C2C市场数据
     * @param list
     */
    void setC2CMarket(List<C2CMarketEntity> list);
}
