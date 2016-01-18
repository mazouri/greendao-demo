package com.mazouri.greendao.db;

import com.mazouri.greendao.db.services.NoteService;

import de.greenrobot.dao.internal.DaoConfig;
import mazouri.greendao.gen.NoteDao;

/**
 * Created by wangdong on 16-1-18.
 */
public class DbUtil {
    private static NoteService noteService;

    private static NoteDao getNoteDao() {
        return DbCore.getDaoSession().getNoteDao();
    }

    public static NoteService getNoteService() {
        if(noteService == null) {
            noteService = new NoteService(getNoteDao());
        }

        return noteService;
    }

}
