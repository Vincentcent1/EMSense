package com.example.android.emsense3.data;

import android.content.ContentValues;
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

    private static final String SQL_DELETE_LIBRARY_TABLE =
            "DROP TABLE IF EXISTS " + LibraryEntry.TABLE_NAME;

    private static final String SQL_DELETE_DATABASE_TABLE =
            "DROP TABLE IF EXISTS " + LibraryDatabaseEntry.TABLE_NAME;

    private static final String SQL_CREATE_LIBRARY_TABLE = "CREATE TABLE " + LibraryEntry.TABLE_NAME + "("
//            + LibraryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LibraryEntry.COLUMN_SERIAL_NUMBER + " TEXT PRIMARY KEY NOT NULL, "
            + LibraryEntry.COLUMN_ITEMS + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_MODEL + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_SPECIFICATIONS + " TEXT NOT NULL);";

    private static final String SQL_CREATE_DATABASE_TABLE = "CREATE TABLE " + LibraryDatabaseEntry.TABLE_NAME + "("
//            + LibraryDatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER + " TEXT PRIMARY KEY NOT NULL, "
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

//        Instantiate Reference database
        ContentValues values = new ContentValues();
        values.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1A");
        values.put(LibraryDatabaseEntry.COLUMN_MODEL, "A");
        values.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");

//        Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values);

        values.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1B");
        values.put(LibraryDatabaseEntry.COLUMN_MODEL, "B");
        values.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");

        newRowId = db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values);

        values.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1C");
        values.put(LibraryDatabaseEntry.COLUMN_MODEL, "C");
        values.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");

        newRowId = db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values);

        values.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "2A");
        values.put(LibraryDatabaseEntry.COLUMN_MODEL, "A");
        values.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Drill");
        values.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");

        newRowId = db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values);

        values.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "2B");
        values.put(LibraryDatabaseEntry.COLUMN_MODEL, "B");
        values.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Drill");
        values.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");

        newRowId = db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values);

        values.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "3A");
        values.put(LibraryDatabaseEntry.COLUMN_MODEL, "A");
        values.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laser Cutter");
        values.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");

        newRowId = db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_LIBRARY_TABLE);
        db.execSQL(SQL_DELETE_DATABASE_TABLE);
        onCreate(db);

    }


}
