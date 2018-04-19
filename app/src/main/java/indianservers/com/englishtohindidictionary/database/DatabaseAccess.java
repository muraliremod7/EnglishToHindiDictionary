package indianservers.com.englishtohindidictionary.database;

/**
 * Created by saisa on 03-10-2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import indianservers.com.englishtohindidictionary.MeaningClass;
import indianservers.com.englishtohindidictionary.TitlesClass;

public class DatabaseAccess{
    private SQLiteOpenHelper openHelper;

    private static DatabaseAccess instance;
    private Context mContext;
    private static final String DATABASE_NAME = "ETGDictionary.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDatabase;
    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    public DatabaseAccess(Context context) {

      this.openHelper = new DatabaseOpenHelper(context);


    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.mDatabase = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (mDatabase != null) {
            this.mDatabase.close();
        }
    }


    public void openDatabase() {
     //   String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();

        this.mDatabase = openHelper.getWritableDatabase();
      //  mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public ArrayList<TitlesClass> getQuotes(String tablename) {
        TitlesClass product = null;
        ArrayList<TitlesClass> productList = new ArrayList<>();
        Log.d("sai", "in open start");
        openDatabase();
        Log.d("sai", "in open end");
        Cursor cursor = mDatabase.rawQuery("select * from "+tablename +" LIMIT 1000", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new TitlesClass( cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("serial")));
            productList.add(product);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return productList;
    }

    public ArrayList<TitlesClass> getQuotess(String tablename,String newtext) {
        TitlesClass product = null;
        ArrayList<TitlesClass> productList = new ArrayList<>();
        Log.d("sai", "in open start");
        openDatabase();
        Log.d("sai", "in open end");
        if(newtext.equals("")){
            Cursor cursor = mDatabase.rawQuery("select * from "+tablename +" LIMIT 1000", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                product = new TitlesClass( cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("serial")));
                productList.add(product);
                cursor.moveToNext();

            }
            cursor.close();
        }else {
            Cursor cursor = mDatabase.rawQuery("select * from "+tablename +" WHERE lower(word) LIKE '"+newtext+"%' order by word", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                product = new TitlesClass( cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("serial")));
                productList.add(product);
                cursor.moveToNext();

            }
            cursor.close();
        }
        closeDatabase();
        return productList;
    }

    public Cursor getData(int id) {
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM hindiwords WHERE serial=?", new String[] {id + ""});
        cursor.moveToFirst();
        String meaning = cursor.getString(cursor.getColumnIndex("word"));
        return cursor;
    }
    public ArrayList<MeaningClass> getMeanings(String id) {
        MeaningClass product = null;
        ArrayList<MeaningClass> productList = new ArrayList<>();
        Log.d("sai", "in open start");
        openDatabase();
        Log.d("sai", "in open end");
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM hindiwords WHERE serial=?", new String[] {id + ""});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new MeaningClass(cursor.getString(cursor.getColumnIndex("serial")),cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("ed")),cursor.getString(cursor.getColumnIndex("ant")),cursor.getString(cursor.getColumnIndex("exm")));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }
}