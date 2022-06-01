package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;
import com.svv.jys.code.common.entity.OtcCoinEntity;

import java.util.List;

/**
 * Created by js on 2018/6/8.
 */

public interface OtcBuyOrSellView extends BaseView {
    void setOtcCoin(List<OtcCoinEntity> list);
    void setOtcAdv(List<OtcAdvEntity.RowsBean> list);
    void setCountry(List<CountryEntity> list);
}
