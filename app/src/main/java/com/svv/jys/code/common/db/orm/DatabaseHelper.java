package com.svv.jys.code.common.db.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.svv.jys.code.common.entity.MarketListEntity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int db_version = 1;
    private static final String TABLE_NAME = "bbwsqlite-test.db";
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, MarketListEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            try {
                TableUtils.dropTable(connectionSource, MarketListEntity.class,
                        true);
                onCreate(database, connectionSource);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * �ر�helper
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
