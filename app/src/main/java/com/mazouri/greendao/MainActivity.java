package com.mazouri.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testGreenDao();
    }

    private void testGreenDao() {

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
