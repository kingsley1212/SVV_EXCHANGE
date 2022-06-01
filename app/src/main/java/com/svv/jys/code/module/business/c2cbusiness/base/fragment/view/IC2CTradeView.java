package com.svv.jys.code.module.business.c2cbusiness.base.fragment.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.C2CCoinInfoEntity;

/**
 * Created by js on 2018/7/11.
 */

public interface IC2CTradeView extends BaseView {

    /**
     * 显示内容
     * @param entity
     */
    void setCoinInfo(C2CCoinInfoEntity entity);

    /**
     * 操作成功
     */
    void buyorsellsuccese();
}
