package com.svv.jys.code.module.business.coinbusiness.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CoinBusinessEntity;
import com.svv.jys.code.common.entity.TradeListEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 * V 1.1.1  Update by Lzj  on 2018/8/22 0930.
 */
public interface ICoinBusinessView extends BaseView {





    /**
     * 设置市场
     */
    void setMarket();

    /**
     * 设置委托
     *
     * @param entity
     */
    void setTrade(TradeListEntity entity);

    /**
     * 移除委托成功
     */
    void removeSuccese();


    /**
     * 显示收藏市场
     *
     * @param list
     */
    void setCollectMarket(List<String> list);



    void setBBData(CoinBusinessEntity entity);
    void setCollectState(List<String> list);

    void setUserData(boolean b);

    void resetCoinNum();


    void submitSuccess(int type,boolean isCheck);


}
