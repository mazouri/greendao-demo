package com.mazouri.greendao.db.services;

import de.greenrobot.dao.AbstractDao;
import mazouri.greendao.gen.Note;

/**
 * Created by wangdong on 16-1-18.
 */
public class NoteService extends BaseService<Note, Long> {
    public NoteService(AbstractDao dao) {
        super(dao);
    }
}
