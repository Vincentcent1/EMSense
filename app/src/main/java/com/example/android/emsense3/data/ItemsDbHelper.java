package com.example.android.emsense3.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.emsense3.Other.Config;
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
            + LibraryEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_YOUTUBE_ID + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_BANNER_ID + " INTEGER NOT NULL, "
            + LibraryEntry.COLUMN_ICON_ID + " INTEGER NOT NULL);";

    private static final String SQL_CREATE_DATABASE_TABLE = "CREATE TABLE " + LibraryDatabaseEntry.TABLE_NAME + "("
//            + LibraryDatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER + " TEXT PRIMARY KEY NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_ITEMS + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_MODEL + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_SPECIFICATIONS + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_YOUTUBE_ID + " TEXT NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_BANNER_ID + " INTEGER NOT NULL, "
            + LibraryDatabaseEntry.COLUMN_ICON_ID + " INTEGER NOT NULL);";

    private static final String SQL_CREATE_IMAGE_TABLE = "CREATE TABLE " + ImageEntry.TABLE_NAME + "("
            + ImageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ImageEntry.COLUMN_SERIAL_NUMBER + " TEXT NOT NULL, "
            + ImageEntry.COLUMN_STEP + " INTEGER NOT NULL, "
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
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Designed for beginners and novices in mind to the world of 3D printing, this affordable and quality machine fits most regular sized homes. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_davincimini_small);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.threedprinter_davincimini_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_DAVINCIMINI);


//        Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1B");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Lulzbot Mini");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "With its simple design, this 3D printer is a high performance desktop machine that is perfect whether you are a home user, architect or engineer.");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_lulzbotmini_small);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.threedprinter_lulzbotmini_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_LULZBOTMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1C");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Makerbot Replicator Mini+");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "A small and compact machine, ideal for 3D printing novices or users with a constraint of space. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_makerbotreplicatormini_small);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.threedprinter_makerbotreplicatormini_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_MAKERBOTREPLICATORMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "1D");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Einstart");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Designed for home users in mind, it features easy operation, intuitive interface with fully fledged 3D printing functionality.");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_einstart_small);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.threedprinter_einstart_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_EINSTART);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "2A");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Epilog Laser Galvo Model G2");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laser Cutter");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "With a unique combination of high-speed laser engraving and marking, this tool is handy for home based to small industry laser cutting projects.");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_lasercutter_epiloglasergalvo_small);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.lasercutter_epiloglasergalvo_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_EPILOGLASERGALVO);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, "2B");
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Thunder Laser Systems Mini-60");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laser Cutter");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "A machine catered to precision and performance, it is ideal for novices looking to learn and create laser cut projects quickly and efficiently. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_lasercutter_thunderlasersystem_small);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.lasercutter_thunderlasersystemsmini_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        ContentValues values2 = new ContentValues();

//        Initialising image database for Laser cutter Epilog Laser Galvo

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 6);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_6);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_6);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 7);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_7);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_7);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 8);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_8);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_8);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 9);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_9);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_9);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 10);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 11);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 12);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 13);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1A");
        values2.put(ImageEntry.COLUMN_STEP, 14);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 6);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 7);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 8);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 9);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 10);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 11);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_6);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_6);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1B");
        values2.put(ImageEntry.COLUMN_STEP, 12);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_7);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_7);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1C");
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1C");
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1C");
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1C");
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1C");
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_setup_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_setup_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_setup_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_setup_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_setup_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_setup_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_setup_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_setup_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 6);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 7);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 8);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "1D");
        values2.put(ImageEntry.COLUMN_STEP, 9);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2A");
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2A");
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2A");
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2A");
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

//        Initialising image database for Laser cutter Thunder Laser Systems Mini

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_connections_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_connections_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_connections_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_connections_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, "2B");
        values2.put(ImageEntry.COLUMN_STEP, 6);
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
