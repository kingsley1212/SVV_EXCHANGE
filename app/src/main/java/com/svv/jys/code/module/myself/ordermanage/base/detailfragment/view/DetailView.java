package com.svv.jys.code.module.myself.ordermanage.base.detailfragment.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.EntrustDetailEntity;
import com.svv.jys.code.common.entity.MarketOrederEntity;

import java.util.List;

/**
 * Created by js on 2018/5/24.
 */

public interface DetailView extends BaseView {

    void setMarket(List<MarketOrederEntity> list);

    /**
     * 初次加载
     * @param list
     */
    void refresh(List<EntrustDetailEntity.RowsBean> list);


    /**
     * 加载更多
     * @param list
     */
    void loading(List<EntrustDetailEntity.RowsBean> list);


}
