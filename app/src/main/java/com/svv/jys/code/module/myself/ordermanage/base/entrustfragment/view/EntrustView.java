package com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.entity.MarketOrederEntity;

import java.util.List;

/**
 * Created by js on 2018/5/24.
 */

public interface EntrustView extends BaseView{
    void setEntrustList(EntrustEntity entrustList);
    void undoSuccess();
    void setMarket(List<MarketOrederEntity> list);
}
