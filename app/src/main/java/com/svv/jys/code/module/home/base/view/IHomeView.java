package com.svv.jys.code.module.home.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.IndexEntity;
import com.svv.jys.code.common.entity.MarketListEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface IHomeView extends BaseView {



    void setBanner(List<IndexEntity.BannerBean> banner);

    void setmarqueeView(List<IndexEntity.NoticeBean> list);
    void setMarketVp(List<List<MarketListEntity>> marketListEntities);

    void setMarketList(List<MarketListEntity> market);
}