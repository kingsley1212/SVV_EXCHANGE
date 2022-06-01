package com.svv.jys.code.module.business.otcbusiness.fragment.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;
import com.svv.jys.code.common.entity.OtcCoinEntity;
import com.svv.jys.code.common.entity.OtcPayEntity;

import java.util.List;

/**
 * Created by js on 2018/8/1.
 */

public interface IOtcBuyOrSellView extends BaseView {
    /**
     * 显示otc交易币种
     * @param list
     */
    void setOtcCoin(List<OtcCoinEntity> list);
    /**
     * 显示otc广告
     * @param list
     */
    void setOtcAdv(List<OtcAdvEntity.RowsBean> list);
    /**
     * 设置国家列表
     * @param list
     */
    void setCountry(List<CountryEntity> list);

    void setPaymMethod(List<OtcPayEntity.RowsBean> list);
}
