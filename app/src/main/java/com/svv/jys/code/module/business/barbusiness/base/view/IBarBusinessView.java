package com.svv.jys.code.module.business.barbusiness.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.entity.TradeListEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface IBarBusinessView extends BaseView {


    void setDelegateData(List<EntrustEntity.RowsBean> list);

    void addDelegateData(List<EntrustEntity.RowsBean> list);

    void setMyPrice(String myPrice);

    void setMarket();

    void setTrade(TradeListEntity entity);

    /**
     * 第一页是否没有数据
     * @param flag
     */
    void isNoData(boolean flag);

    void removeSuccese();

    void setLtUserinfo(LtUserCoinInfoentity ltUserCoinInfoentity);

    void postEntrusSuccese();

    void setMaxNum(String maxNum);

    void setCollectMarket(List<String> list);

    /**
     * 显示大概的法币价格
     *
     * @param multiply
     */
    void setGFbPriceTx(String multiply);
}
