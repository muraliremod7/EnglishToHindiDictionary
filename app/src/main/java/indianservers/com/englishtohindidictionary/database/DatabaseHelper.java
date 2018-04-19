package indianservers.com.englishtohindidictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import indianservers.com.englishtohindidictionary.MeaningClass;

/**
 * Created by JNTUH on 24-03-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "favourates";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_MEANING = "meaning";
    public static final String COLUMN_ANTONYMS = "antonyms";
    public static final String COLUMN_EXAMPLE = "example";
    public static final String COLUMN_ADJECTIVE = "adjective";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "favourates";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_WORD + " TEXT,"
                    + COLUMN_MEANING + " TEXT,"
                    + COLUMN_ANTONYMS + " TEXT,"
                    + COLUMN_EXAMPLE + " TEXT,"
                    + COLUMN_ADJECTIVE + " TEXT"
                    + ")";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
    public void insertNote(String word,String meaning,String antonyms,String example,String adjective) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(COLUMN_WORD, word);
        values.put(COLUMN_MEANING, meaning);
        values.put(COLUMN_ANTONYMS, antonyms);
        values.put(COLUMN_EXAMPLE, example);
        values.put(COLUMN_ADJECTIVE, adjective);
        // insert row
        long id = db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
    }
    public ArrayList<MeaningClass> getAllNotes() {
        ArrayList<MeaningClass> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " +
                COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MeaningClass note = new MeaningClass();
                note.setWord(cursor.getString(cursor.getColumnIndex(COLUMN_WORD)));
                note.setSerial(cursor.getString(cursor.getColumnIndex(COLUMN_MEANING)));
                note.setAnt(cursor.getString(cursor.getColumnIndex(COLUMN_ANTONYMS)));
                note.setExm(cursor.getString(cursor.getColumnIndex(COLUMN_EXAMPLE)));
                note.setEd(cursor.getString(cursor.getColumnIndex(COLUMN_ADJECTIVE)));
                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }
}
