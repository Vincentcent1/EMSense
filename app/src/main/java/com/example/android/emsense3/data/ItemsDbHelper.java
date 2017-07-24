package com.example.android.emsense3.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.emsense3.R;
import com.example.android.emsense3.data.ItemsContract.ImageEntry;
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

    private static final String SQL_DELETE_IMAGE_TABLE =
            "DROP TABLE IF EXISTS " + ImageEntry.TABLE_NAME;

    private static final String SQL_CREATE_LIBRARY_TABLE = "CREATE TABLE " + LibraryEntry.TABLE_NAME + "("
//            + LibraryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LibraryEntry.COLUMN_SERIAL_NUMBER + " TEXT PRIMARY KEY NOT NULL, "
            + LibraryEntry.COLUMN_ITEMS + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_MODEL + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_SPECIFICATIONS + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_BANNER_ID + " INTEGER NOT NULL, "
            + LibraryEntry.COLUMN_IMAGE_ID + " INTEGER NOT NULL);";

    private static final String SQL_CREATE_DATABASE_TABLE = "CREATE TABLE " + LibraryDatabaseEntry.TABLE_NAME + "("
//            + LibraryDatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER + " TEXT PRIMARY KEY NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_ITEMS + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_MODEL + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_SPECIFICATIONS + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_BANNER_ID + " INTEGER NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_IMAGE_ID + " INTEGER NOT NULL);";

    private static final String SQL_CREATE_IMAGE_TABLE = "CREATE TABLE " + ImageEntry.TABLE_NAME + "("
            + LibraryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ImageEntry.COLUMN_SERIAL_NUMBER + " TEXT NOT NULL, "
            + ImageEntry.COLUMN_STEP + " TEXT NOT NULL, "
            + ImageEntry.COLUMN_IMAGE_ID + " INTEGER NOT NULL, "
            + ImageEntry.COLUMN_TEXT_ID + " INTEGER NOT NULL);";

    public ItemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LIBRARY_TABLE);
        db.execSQL(SQL_CREATE_DATABASE_TABLE);
        db.execSQL(SQL_CREATE_IMAGE_TABLE);

//        Instantiate Reference database
        ContentValues values1 = new ContentValues();
        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1A");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "da Vinci Mini");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_davincimini_small);
        values1.put(LibraryDatabaseEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_icon);


//        Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1B");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Lulzbot Mini");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_lulzbotmini_small);
        values1.put(LibraryDatabaseEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_icon);


        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1C");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Makerbot Replicator Mini+");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_makerbotreplicatormini_small);
        values1.put(LibraryDatabaseEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_icon);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1D");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Einstart");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_einstart_small);
        values1.put(LibraryDatabaseEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_icon);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "2A");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Epilog Laser Galvo Model G2");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laser Cutter");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_lasercutter_epiloglasergalvo_small);
        values1.put(LibraryDatabaseEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_icon);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "2B");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Thunder Laser Systems Mini-60");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laser Cutter");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_lasercutter_thunderlasersystem_small);
        values1.put(LibraryDatabaseEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_icon);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        ContentValues values2 = new ContentValues();

//        Initialising image database for Laser cutter Epilog Laser Galvo

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2A");
        values2.put(ImageEntry.COLUMN_STEP, "1");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2A");
        values2.put(ImageEntry.COLUMN_STEP, "2");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2A");
        values2.put(ImageEntry.COLUMN_STEP, "3");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2A");
        values2.put(ImageEntry.COLUMN_STEP, "4");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

//        Initialising image database for Laser cutter Thunder Laser Systems Mini

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, "1");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, "2");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, "3");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, "4");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_connections_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_connections_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, "5");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_connections_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_connections_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, "6");
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_connections_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_connections_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_LIBRARY_TABLE);
        db.execSQL(SQL_DELETE_DATABASE_TABLE);
        db.execSQL(SQL_DELETE_IMAGE_TABLE);
        onCreate(db);

    }


}
