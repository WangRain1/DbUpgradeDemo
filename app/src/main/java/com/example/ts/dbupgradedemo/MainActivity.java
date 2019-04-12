/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.example.ts.dbupgradedemo;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper dbHelper = new DbHelper(this);
        findViewById(R.id.query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CustomColor> colors = DbHelper.getCustomColors(AmbienceDbContract.CustomColorEntry.TABLE_NAME);
                for (CustomColor c : colors){
                    Log.e("MainActivity",c.getTime()+"--------" + c.getLocationX());
                }
            }
        });

        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomColor color = new CustomColor(1, new PointF(100,100));
                color.setTime(System.currentTimeMillis()+"");
                DbHelper.insertCustomColor((color), AmbienceDbContract.CustomColorEntry
                        .TABLE_NAME);
                Log.e("MainActivity","----getTime----" + color.getTime());
                DbHelper.insertCustomColor(new CustomColor(2, new PointF(200,200)), AmbienceDbContract.CustomColorEntry
                        .TABLE_NAME);
                DbHelper.insertCustomColor(new CustomColor(3, new PointF(300,300)), AmbienceDbContract.CustomColorEntry
                        .TABLE_NAME);
            }
        });
    }
}
