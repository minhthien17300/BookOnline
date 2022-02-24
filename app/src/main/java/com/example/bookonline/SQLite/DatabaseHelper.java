package com.example.bookonline.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper{
    public  static final  String DBNAME = "Cartogary.db";
    public  static final  int DBVERSION = 1;
    public  static final  String TABLE = "GioHang";
    public  static final  String COLUMN1 = "id_SP";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null,DBVERSION);
    }

    @Override

    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop table if exists User ");
    }
}
