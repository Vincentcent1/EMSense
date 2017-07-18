package com.example.android.emsense3.data;

import android.provider.BaseColumns;

/**
 * Created by setia on 7/17/2017.
 */

public final class ItemsContract {
    private ItemsContract() {
    }

    ;

    public static class LibraryEntry implements BaseColumns {
        public static final String TABLE_NAME = "library";

        public static final String _ID = "id";
        public static final String COLUMN_SERIAL_NUMBER = "serialNumber";
        public static final String COLUMN_ITEMS = "items";
        public static final String COLUMN_MODEL = "model";
        public static final String COLUMN_SPECIFICATIONS = "specs";


    }

    public static class LibraryDatabaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "database";

        public static final String _ID = "id";
        public static final String COLUMN_SERIAL_NUMBER = "serialNumber";
        public static final String COLUMN_ITEMS = "items";
        public static final String COLUMN_MODEL = "model";
        public static final String COLUMN_SPECIFICATIONS = "specs";
    }


}
