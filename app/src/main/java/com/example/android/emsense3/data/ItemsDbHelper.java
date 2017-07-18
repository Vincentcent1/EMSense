package com.example.android.emsense3.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.emsense3.data.ItemsContract.LibraryDatabaseEntry;
import com.example.android.emsense3.data.ItemsContract.LibraryEntry;

/**
 * Created by setia on 7/17/2017.
 */

public class ItemsDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LibraryEntry.TABLE_NAME;

    private static final String SQL_CREATE_LIBRARY_TABLE = "CREATE TABLE " + LibraryEntry.TABLE_NAME + "("
            + LibraryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LibraryEntry.COLUMN_SERIAL_NUMBER + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_ITEMS + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_MODEL + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_SPECIFICATIONS + " TEXT NOT NULL);";

    private static final String SQL_CREATE_DATABASE_TABLE = "CREATE TABLE " + LibraryDatabaseEntry.TABLE_NAME + "("
            + LibraryDatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_ITEMS + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_MODEL + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_SPECIFICATIONS + " TEXT NOT NULL);";

    public ItemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LIBRARY_TABLE);
        db.execSQL(SQL_CREATE_DATABASE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }


}
