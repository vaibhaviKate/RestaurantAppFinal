package com.example.android.restuarantfinder;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class dataBaseOpenHelper extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "restaurant1.db";
    private static final int DATABASE_VERSION = 2;

    public dataBaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
