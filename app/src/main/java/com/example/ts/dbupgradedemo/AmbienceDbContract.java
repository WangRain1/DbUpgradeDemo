/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.example.ts.dbupgradedemo;

import android.provider.BaseColumns;

/**
 * Contract of database.
 */
public class AmbienceDbContract {

    /**
     * To prevent someone from accidentally instantiating the contract class,
     * make the constructor private.
     */
    private AmbienceDbContract() {
    }

    /**
     * Inner class that defines the table contents.
     */
    public static class CustomColorEntry implements BaseColumns {
        /** Table name. */
        public static final String TABLE_NAME = "custom_color";
        /** Column name of color. */
        public static final String COLUMN_NAME_COLOR = "color";
        /** Column name of locationX. */
        public static final String COLUMN_NAME_LOCX = "locationX";
        /** Column name of locationY. */
        public static final String COLUMN_NAME_LOCY = "locationY";

        public static final String COLUMN_NAME_TIME = "time";
    }
}
