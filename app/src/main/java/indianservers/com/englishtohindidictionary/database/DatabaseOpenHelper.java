package indianservers.com.englishtohindidictionary.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by saisa on 03-10-2016.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "ETGDictionary.db";
    private static final int DATABASE_VERSION = 2;
    private SQLiteOpenHelper mOpenHelper;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



}
