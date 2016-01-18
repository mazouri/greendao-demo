package com.mazouri.greendao.db;

import android.content.Context;

import de.greenrobot.dao.query.QueryBuilder;
import mazouri.greendao.gen.DaoMaster;
import mazouri.greendao.gen.DaoSession;

/**
 * Created by wangdong on 16-1-18.
 */
public class DbCore {

    private static final String DEFAULT_DB_NAME = "default.db";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private static Context mContext;
    private static String DB_NAME;

    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }

    public static void init(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        mContext = context.getApplicationContext();
        DB_NAME = dbName;
    }

    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DatabaseHelper helper = new DatabaseHelper(mContext, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static void enableQueryBuilderLog(){

        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
