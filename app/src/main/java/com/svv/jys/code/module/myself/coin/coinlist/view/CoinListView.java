package com.svv.jys.code.module.myself.coin.coinlist.view;


import com.svv.jys.code.common.base.mvp.BaseView;

import java.util.List;

/**
 * Created by LB on 2018/3/2.
 */

public interface CoinListView extends BaseView {
    void setData(List<String> list);

    void getAddressError();

    void getCoinFalse();

}
