package com.svv.jys.code.module.myself.myaddress.base.coin.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CoinEntity;

import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public interface CoinView extends BaseView{
    void setCoin(List<CoinEntity> list);
}
