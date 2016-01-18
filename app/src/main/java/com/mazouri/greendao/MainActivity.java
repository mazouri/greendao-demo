package com.mazouri.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mazouri.greendao.db.DbUtil;
import com.mazouri.greendao.db.services.NoteService;

import java.util.Date;

import mazouri.greendao.gen.DaoMaster;
import mazouri.greendao.gen.DaoSession;
import mazouri.greendao.gen.Note;
import mazouri.greendao.gen.NoteDao;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private NoteDao noteDao;

    private NoteService noteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testGreenDao();
    }

    private void testGreenDao() {

        //方式1 基本使用
        devOpenHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        noteDao = daoSession.getNoteDao();

        String noteText = "this is noteText";
        String comment = "this is note comment";

        Note note = new Note(null, noteText, comment, new Date());
        noteDao.insert(note);
        Log.d(TAG, "Inserted new note, ID : " + note.getId());
        Log.d(TAG, "Inserted new note, text : " + note.getText());


        //推荐 方式2
        String noteText2 = "this is noteText2";
        String comment2 = "this is note comment2";

        noteService = DbUtil.getNoteService();
        Note note2 = new Note(null, noteText2, comment2, new Date());
        noteService.save(note2);

        Log.d(TAG, "Inserted new note, ID : " + note2.getId());
        Log.d(TAG, "Inserted new note, text : " + note2.getText());

        note2.setText("change note text");
        noteService.saveOrUpdate(note2);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
