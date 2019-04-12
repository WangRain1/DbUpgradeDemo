/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.example.ts.dbupgradedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PointF;

import com.example.ts.dbupgradedemo.AmbienceDbContract.CustomColorEntry;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    static DbFactory mFactory;
    public DbHelper(Context c) {
        mFactory = new DbFactory(c);
    }

    public static List<CustomColor> getCustomColors(String tableName) {
        SQLiteDatabase database = mFactory.getDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + tableName, new String[]{});

        ArrayList<CustomColor> customColors = new ArrayList<>();
        if (cursor.moveToFirst()) {

            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(CustomColorEntry._ID));
                int color = cursor.getInt(cursor.getColumnIndexOrThrow(CustomColorEntry.COLUMN_NAME_COLOR));
                float locationX = cursor.getFloat(cursor.getColumnIndexOrThrow(CustomColorEntry.COLUMN_NAME_LOCX));
                float locationY = cursor.getFloat(cursor.getColumnIndexOrThrow(CustomColorEntry.COLUMN_NAME_LOCY));
                String t = cursor.getString(cursor.getColumnIndexOrThrow(CustomColorEntry.COLUMN_NAME_TIME));
                CustomColor customColor = new CustomColor(id, color, new PointF(locationX, locationY));
                customColor.setTime(t);
                customColors.add(customColor);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return customColors;
    }

    /**
     * Insert custom color.
     *
     * @param customColor custom color
     */
    public static void insertCustomColor(CustomColor customColor, String tabName) {

        if (null != customColor) {
            final SQLiteDatabase database = mFactory.getDatabase();
            ContentValues values = new ContentValues();
            values.put(CustomColorEntry.COLUMN_NAME_COLOR, customColor.getColor());
            values.put(CustomColorEntry.COLUMN_NAME_LOCX, customColor.getLocationX());
            values.put(CustomColorEntry.COLUMN_NAME_LOCY, customColor.getLocationY());
            values.put(CustomColorEntry.COLUMN_NAME_TIME, customColor.getTime());
            database.insert(tabName, null, values);
        }
    }
}
