package com.svv.jys.code.module.business.tradechat.base3.view;

import com.github.fujianlian.klinechart.KLineEntity;
import com.svv.jys.code.common.base.mvp.BaseView;

import java.util.List;

/**
 * Created by lzj on 2018/6/26.
 */

public interface ITradeChat3View extends BaseView {

    void setMarket();

    void klineshowloading();

    void setKlineData(List<KLineEntity> s);

    void klineDatanotify(String status, KLineEntity kLineEntity);
}
