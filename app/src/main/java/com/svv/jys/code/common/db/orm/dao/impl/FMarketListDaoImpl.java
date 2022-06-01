package com.svv.jys.code.common.db.orm.dao.impl;

import android.content.Context;

import com.svv.jys.code.common.db.orm.DatabaseHelper;
import com.svv.jys.code.common.db.orm.dao.IFMarketListDao;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2018/5/23.
 */

public class FMarketListDaoImpl implements IFMarketListDao {

    private Dao<MarketListEntity, String> fOpe;

    public FMarketListDaoImpl(Context context) {
        try {
            fOpe = DatabaseHelper.getInstance(context).getDao(MarketListEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean setAllData(List<MarketListEntity> listEntities) {
        try {
            fOpe.delete(fOpe.queryForAll());
            fOpe.create(listEntities);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<MarketListEntity> getDataByArea(String area) {
        try {
            return fOpe.queryForEq("jiaoyiqu", area);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<MarketListEntity>();
        }
    }

}
