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

    //Storing each item's serial number in a String object
    private static final String threedprinter_davincimini_serialnumber = "1A";
    private static final String threedprinter_lulzbotmini_serialnumber = "1B";
    private static final String threedprinter_makerbotreplicatormini_serialnumber = "1C";
    private static final String threedprinter_einstart_serialnumber = "1D";
    private static final String lasercutter_epiloglasergalvo_serialnumber = "2A";
    private static final String lasercutter_thunderlasersystemsmini_serialnumber = "2B";
    private static final String laptop_hp15ac648tx_serialnumber = "3A";
    private static final String laptop_asusk501u_serialnumber = "3B";
    private static final String laptop_aceraspiree14_serialnumber = "3C";
    private static final String laptop_aceraspirevn7_serialnumber = "3D";
    private static final String laptop_aftershocksl15_serialnumber = "3E";
    private static final String laptop_asusx550v_serialnumber = "3F";
    private static final String laptop_dellxps13_serialnumber = "3G";
    private static final String laptop_dellxps15_serialnumber = "3H";
    private static final String laptop_lenovoyoga13_serialnumber = "3I";
    private static final String phone_asuszenfone3_serialnumber = "4A";
    private static final String phone_huaweip9_serialnumber = "4B";
    private static final String phone_lgv20_serialnumber = "4C";
    private static final String phone_samsunggalaxya7_serialnumber = "4D";
    private static final String phone_samsunggalaxynote5_serialnumber = "4E";
    private static final String phone_samsunggalaxys6_serialnumber = "4F";
    private static final String phone_xiaomiredminote2_serialnumber = "4G";
    private static final String phone_xiaomiredminote4x_serialnumber = "4H";


    // Static Array to store all the serial numbers. This is used to check if the serial number entered is in the database
    public static final String[] serialNumberStringArray = new String[]{
            threedprinter_davincimini_serialnumber,
            threedprinter_lulzbotmini_serialnumber,
            threedprinter_makerbotreplicatormini_serialnumber,
            threedprinter_einstart_serialnumber,
            lasercutter_epiloglasergalvo_serialnumber,
            lasercutter_thunderlasersystemsmini_serialnumber,
            laptop_asusk501u_serialnumber,
            laptop_hp15ac648tx_serialnumber,
            laptop_aceraspiree14_serialnumber,
            laptop_aftershocksl15_serialnumber,
            laptop_aceraspirevn7_serialnumber,
            laptop_asusx550v_serialnumber,
            laptop_dellxps13_serialnumber,
            laptop_lenovoyoga13_serialnumber,
            laptop_dellxps15_serialnumber,
            phone_asuszenfone3_serialnumber,
            phone_huaweip9_serialnumber,
            phone_lgv20_serialnumber,
            phone_samsunggalaxya7_serialnumber,
            phone_samsunggalaxynote5_serialnumber,
            phone_samsunggalaxys6_serialnumber,
            phone_xiaomiredminote2_serialnumber,
            phone_xiaomiredminote4x_serialnumber

    };


    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_DELETE_LIBRARY_TABLE =
            "DROP TABLE IF EXISTS " + LibraryEntry.TABLE_NAME;

    private static final String SQL_DELETE_DATABASE_TABLE =
            "DROP TABLE IF EXISTS " + LibraryDatabaseEntry.TABLE_NAME;

    private static final String SQL_DELETE_IMAGE_TABLE =
            "DROP TABLE IF EXISTS " + ImageEntry.TABLE_NAME;

    //Writing the SQL command to create the respective tables
    private static final String SQL_CREATE_LIBRARY_TABLE = "CREATE TABLE " + LibraryEntry.TABLE_NAME + "("
            + LibraryEntry.COLUMN_SERIAL_NUMBER + " TEXT PRIMARY KEY NOT NULL, "
            + LibraryEntry.COLUMN_ITEMS + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_MODEL + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_SPECIFICATIONS + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_YOUTUBE_ID + " TEXT NOT NULL, "
            + LibraryEntry.COLUMN_BANNER_ID + " INTEGER NOT NULL, "
            + LibraryEntry.COLUMN_ICON_ID + " INTEGER NOT NULL);";

    private static final String SQL_CREATE_DATABASE_TABLE = "CREATE TABLE " + LibraryDatabaseEntry.TABLE_NAME + "("
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


//        Instantiate Internal database with each objects, we use local database instead of cloud.
        ContentValues values1 = new ContentValues();
        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "da Vinci Mini");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Designed for beginners and novices in mind to the world of 3D printing, this affordable and quality machine fits most regular sized homes. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_davincimini);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.threedprinter_davincimini_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_DAVINCIMINI);


        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Lulzbot Mini");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "With its simple design, this 3D printer is a high performance desktop machine that is perfect whether you are a home user, architect or engineer.");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_lulzbotmini);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.threedprinter_lulzbotmini_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_LULZBOTMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, threedprinter_makerbotreplicatormini_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Makerbot Replicator Mini+");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "A small and compact machine, ideal for 3D printing novices or users with a constraint of space. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_makerbotreplicatormini);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.threedprinter_makerbotreplicatormini_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_MAKERBOTREPLICATORMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Einstart");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "3D Printer");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Designed for home users in mind, it features easy operation, intuitive interface with fully fledged 3D printing functionality.");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_threedprinter_einstart);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.threedprinter_einstart_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_EINSTART);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, lasercutter_epiloglasergalvo_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Epilog Laser Galvo Model G2");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laser Cutter");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "With a unique combination of high-speed laser engraving and marking, this tool is handy for home based to small industry laser cutting projects.");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_lasercutter_epiloglasergalvo);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.lasercutter_epiloglasergalvo_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_EPILOGLASERGALVO);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, lasercutter_thunderlasersystemsmini_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Thunder Laser Systems Mini-60");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laser Cutter");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "A machine catered to precision and performance, it is ideal for novices looking to learn and create laser cut projects quickly and efficiently. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_lasercutter_thunderlasersystem);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.lasercutter_thunderlasersystemsmini_icon);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_hp15ac648tx_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "HP 15 - AC648TX");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the HP 15 - AC648TX empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_hp15ac648tx);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_hp15ac648tx);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_asusk501u_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "ASUS K501U");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the ASUS K501U empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_asusk501u);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_asusk501u);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_aceraspiree14_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "acer Aspire E14");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the acer Aspire E14 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_aceraspiree14);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_aceraspiree14);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_aceraspirevn7_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "acer Aspire VN7");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the acer Aspire VN7 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_aceraspirevn7);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_aceraspirevn7);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_aftershocksl15_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Aftershock SL-15");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the Aftershock SL-15 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_aftershocksl15);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_aftershocksl15);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_asusx550v_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "ASUS X550V");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the ASUS X550V empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_asus_x550v);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_asus_x550v);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_dellxps13_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "DELL XPS 13");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the DELL XPS 13 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_dellxps13);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_dellxps13);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_dellxps15_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "DELL XPS 15");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the DELL XPS 15 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_dellxps15);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_dellxps15);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, laptop_lenovoyoga13_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Lenovo Yoga 13");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Laptop");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the Lenovo Yoga 13 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_laptop_lenovoyoga13);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_laptop_lenovoyoga13);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, phone_asuszenfone3_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "ASUS Zenfone 3");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Mobile Phone");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the ASUS Zenfone 3 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_phone_asuszenfone3);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_phone_asuszenfone3);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, phone_huaweip9_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Huawei P9");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Mobile Phone");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the Huawei P9 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_phone_huaweip9);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_phone_huaweip9);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, phone_lgv20_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "LG V20");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Mobile Phone");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the LG V20 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_phone_lgv20);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_phone_lgv20);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, phone_samsunggalaxya7_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Samsung Galaxy A7");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Mobile Phone");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the Samsung Galaxy A7 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_phone_samsunggalaxya7);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_phone_samsunggalaxya7);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, phone_samsunggalaxynote5_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Samsung Galaxy Note 5");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Mobile Phone");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the Samsung Galaxy Note 5 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_phone_samsunggalaxynote5);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_phone_samsunggalaxynote5);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, phone_samsunggalaxys6_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Samsung Galaxy S6");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Mobile Phone");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the Samsung Galaxy S6 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_phone_samsunggalaxys6);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_phone_samsunggalaxys6);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, phone_xiaomiredminote2_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Xiaomi Redmi Note 2");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Mobile Phone");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the Xiaomi Redmi Note 2 empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_phone_xiaomiredminote2);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_phone_xiaomiredminote2);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        values1.put(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER, phone_xiaomiredminote4x_serialnumber);
        values1.put(LibraryDatabaseEntry.COLUMN_MODEL, "Xiaomi Redmi Note 4X");
        values1.put(LibraryDatabaseEntry.COLUMN_ITEMS, "Mobile Phone");
        values1.put(LibraryDatabaseEntry.COLUMN_DESCRIPTION, "Impressively thin and light, the Xiaomi Redmi Note 4X empowers users to create, connect, and collaborate, using enterprise-class performance technology that helps keep you productive in and out of the office. ");
        values1.put(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS, "Specifications stuff here");
        values1.put(LibraryDatabaseEntry.COLUMN_BANNER_ID, R.drawable.banner_phone_xiaomiredminote4x);
        values1.put(LibraryDatabaseEntry.COLUMN_ICON_ID, R.drawable.banner_phone_xiaomiredminote4x);
        values1.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, Config.YOUTUBE_VIDEO_CODE_THUNDERLASERSYSTEMSMINI);

        db.insert(LibraryDatabaseEntry.TABLE_NAME, null, values1);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Initialising internal database for each of the tutorial page. Each page has an image and a textview.//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        ContentValues values2 = new ContentValues();


        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 6);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_6);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_6);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 7);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_7);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_7);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 8);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_8);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_8);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 9);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_setup_9);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_setup_9);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 10);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 11);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 12);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 13);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_davincimini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 14);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_davincimini_filament_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_davincimini_filament_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        //        Initialising image database for 3D Printer Lulzbot Mini


        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 6);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 7);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 8);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 9);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_filament_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_filament_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 10);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 11);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_6);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_6);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_lulzbotmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 12);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_lulzbotmini_setup_7);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_lulzbotmini_setup_7);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_makerbotreplicatormini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_makerbotreplicatormini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_makerbotreplicatormini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_makerbotreplicatormini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_makerbotreplicatormini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.drawable.threedprinter_makerbotreplicatormini_setup_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_setup_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_setup_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_setup_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_setup_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_setup_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_setup_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_setup_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_setup_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 6);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 7);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 8);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, threedprinter_einstart_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 9);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.threedprinter_einstart_filament_5);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.threedprinter_einstart_filament_5);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_epiloglasergalvo_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_epiloglasergalvo_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_epiloglasergalvo_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_epiloglasergalvo_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_epiloglasergalvo_general_4);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_epiloglasergalvo_general_4);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_thunderlasersystemsmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 1);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_thunderlasersystemsmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 2);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_thunderlasersystemsmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 3);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_general_3);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_general_3);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_thunderlasersystemsmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 4);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_connections_1);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_connections_1);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_thunderlasersystemsmini_serialnumber);
        values2.put(ImageEntry.COLUMN_STEP, 5);
        values2.put(ImageEntry.COLUMN_IMAGE_ID, R.drawable.lasercutter_thunderlasersystemsmini_connections_2);
        values2.put(ImageEntry.COLUMN_TEXT_ID, R.string.lasercutter_thunderlasersystemsmini_connections_2);

        db.insert(ImageEntry.TABLE_NAME, null, values2);

        values2.put(ImageEntry.COLUMN_SERIAL_NUMBER, lasercutter_thunderlasersystemsmini_serialnumber);
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
