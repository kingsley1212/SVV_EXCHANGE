package com.svv.jys.code.module.myself.myasset.jiaoyi.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CunFangSetEntity;
import com.svv.jys.code.common.entity.WalletDataEntity;

/**
 * Created by js on 2018/5/26.
 */

public interface TradingAccountView extends BaseView{
    String getRySize();

    void putData(WalletDataEntity entity, boolean isLoadMore);

    void setNumBank(CunFangSetEntity s);
}
