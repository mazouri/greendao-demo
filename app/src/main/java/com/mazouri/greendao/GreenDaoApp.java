package com.mazouri.greendao;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.mazouri.greendao.db.DbCore;
import com.mazouri.greendao.utils.log.EasyLog;

/**
 * Created by wangdong on 16-1-18.
 */
public class GreenDaoApp extends Application {
    private static final String TAG = GreenDaoApp.class.getSimpleName();
    private static GreenDaoApp mInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        DbCore.init(this);
//        DbCore.enableQueryBuilderLog();
    }

    public static GreenDaoApp get(Context context) {
        return (GreenDaoApp) context.getApplicationContext();
    }

    public static GreenDaoApp getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        if (EasyLog.DEBUG) {
            EasyLog.d(TAG, "onTerminate called");
        }

        super.onTerminate();

        System.exit(0);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }
}
