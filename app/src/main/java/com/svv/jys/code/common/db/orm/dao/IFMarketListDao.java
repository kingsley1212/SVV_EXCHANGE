package com.svv.jys.code.common.db.orm.dao;

import com.svv.jys.code.common.entity.MarketListEntity;

import java.util.List;

/**
 * Created by lzj on 2018/5/23.
 */

public interface IFMarketListDao {
    //设置所有数据
    Boolean setAllData(List<MarketListEntity> listEntities);

    //根据市场ID获取数据
    List<MarketListEntity> getDataByArea(String area);
}
