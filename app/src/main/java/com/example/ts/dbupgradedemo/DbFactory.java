/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.example.ts.dbupgradedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.PointF;
import android.util.Log;

import com.example.ts.dbupgradedemo.AmbienceDbContract.CustomColorEntry;

import java.util.ArrayList;
import java.util.List;

public class DbFactory extends SQLiteOpenHelper {

    SQLiteDatabase mDatabase;

    public DbFactory(Context context) {
        super(context, "db_upgrade.db", null, 2);
    }

    public SQLiteDatabase getDatabase(){
        if (null == mDatabase){
            mDatabase = getReadableDatabase();
        }
       return mDatabase;
    }

    /**
     * version = 1;
     * auto     integer   REAL      REAL
     * _id     color     loc_x     loc_y
     *
     * version = 2; 添加了一个 字段 time
     * auto     integer   REAL         REAL       REAL
     * _id     color     loc_x        loc_y       time
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CustomColorEntry.TABLE_NAME
                + " (" + CustomColorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CustomColorEntry.COLUMN_NAME_COLOR + " INTEGER,"
                + CustomColorEntry.COLUMN_NAME_LOCX + " REAL,"
                + CustomColorEntry.COLUMN_NAME_LOCY + " REAL)";
        Log.e("----DbFactory----","-----onCreate---");
        db.execSQL(createTable);
    }

    //临时表名
    String snapCachedTableName = CustomColorEntry.TABLE_NAME + "snap_cached";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.e("----DbFactory----","-----onUpgrade---");

        //创建临时缓存表 用于缓存老表里面的数据
        String createTable = "CREATE TABLE " + snapCachedTableName
                + " (" + CustomColorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CustomColorEntry.COLUMN_NAME_COLOR + " INTEGER,"
                + CustomColorEntry.COLUMN_NAME_LOCX + " REAL,"
                + CustomColorEntry.COLUMN_NAME_LOCY + " REAL)";
        db.execSQL(createTable);
        //查询所有的老表数据
        List<CustomColor> colors = getCustomColors(CustomColorEntry.TABLE_NAME,db);
        //把数据插入到缓存的表中去
        for (CustomColor color : colors){
            insertCustomColor(color, snapCachedTableName,db);
        }
        db.execSQL("drop table " + CustomColorEntry.TABLE_NAME);
        //创建新的表结构
        String createNewTable = "CREATE TABLE " + CustomColorEntry.TABLE_NAME
                + " (" + CustomColorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CustomColorEntry.COLUMN_NAME_COLOR + " INTEGER,"
                + CustomColorEntry.COLUMN_NAME_LOCX + " REAL,"
                + CustomColorEntry.COLUMN_NAME_LOCY + " REAL,"
                + CustomColorEntry.COLUMN_NAME_TIME + " REAL)";
        db.execSQL(createNewTable);
        //查询所有的缓存表中的数据
        List<CustomColor> cachedColors = getCustomColors(snapCachedTableName,db);
        //把数据插入到新的表中去
        for (CustomColor color : cachedColors){
            insertCustomColor(color, CustomColorEntry.TABLE_NAME,db);
        }
        db.execSQL("drop table " + snapCachedTableName);
    }

    public static List<CustomColor> getCustomColors(String tableName,SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + tableName, new String[]{});
        ArrayList<CustomColor> customColors = new ArrayList<>();
        if (cursor.moveToFirst()) {

            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(CustomColorEntry._ID));
                int color = cursor.getInt(cursor.getColumnIndexOrThrow(CustomColorEntry.COLUMN_NAME_COLOR));
                float locationX = cursor.getFloat(cursor.getColumnIndexOrThrow(CustomColorEntry.COLUMN_NAME_LOCX));
                float locationY = cursor.getFloat(cursor.getColumnIndexOrThrow(CustomColorEntry.COLUMN_NAME_LOCY));

                CustomColor customColor = new CustomColor(id, color, new PointF(locationX, locationY));
                customColors.add(customColor);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return customColors;
    }

    public static void insertCustomColor(CustomColor customColor, String tabName,SQLiteDatabase database) {

        if (null != customColor) {
            ContentValues values = new ContentValues();
            values.put(CustomColorEntry.COLUMN_NAME_COLOR, customColor.getColor());
            values.put(CustomColorEntry.COLUMN_NAME_LOCX, customColor.getLocationX());
            values.put(CustomColorEntry.COLUMN_NAME_LOCY, customColor.getLocationY());
            database.insert(tabName, null, values);
        }
    }
}
