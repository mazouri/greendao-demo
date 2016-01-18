package com.mazouri.greendao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.mazouri.greendao.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mazouri.greendao.gen.DaoMaster;

/**
 * Created by wangdong on 16-1-18.
 */
public class DatabaseHelper extends DaoMaster.DevOpenHelper {
    public static final String DB_FILE = "greendao_demo.db";
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private Context mContext;

    public DatabaseHelper(Context context, String name, CursorFactory factory) {
        super(context, name, factory);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        super.onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    private boolean isDatabaseExists() {
        final File dbFile = mContext.getDatabasePath(DB_FILE);
        return dbFile.exists();
    }

    private void copyDatabaseFile() throws IOException {
        final File dbPath = mContext.getDatabasePath(DB_FILE);
        dbPath.mkdirs();
        if(dbPath.exists()) {
            Log.d(TAG, "dbPath exists in");
            dbPath.delete();
            dbPath.createNewFile();
        }
        Log.d(TAG, "dbPath after  exists ");

        final InputStream input = mContext.getAssets().open(DB_FILE);
        final OutputStream output = new FileOutputStream(dbPath);

        Utils.copyStream(input, output);

        input.close();
        output.close();
    }
}
