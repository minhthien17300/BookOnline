package com.example.bookonline.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    FirebaseDatabase rootNode; //f_instanse
    DatabaseReference userref; //f_db
    private  static  DatabaseAccess instance;
    Cursor c= null;
    public  String iduser;
    Map<String,String> user; // Map lưu dữ liệu dạng String : String --> Hoten: Thien
    Map<String,Long> diem; //Firebase sử dụng kiểu Long thay vì Int
    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseHelper(context);

    }

    public  static  DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }
    public  void close(){
        if(db!=null){
            this.db.close();
        }
    }
    public void insertData(String idsp,Integer soluong )
    {
        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_SP",idsp);
        contentValues.put("SoLuong",soluong);
        long result = db.insert("GioHang",null,contentValues);
    }

    public Boolean changeData(String idsp,Integer soluong )
    {
        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SoLuong",soluong);
        return db.update(DatabaseHelper.TABLE,contentValues,DatabaseHelper.COLUMN1+"=?",new String[]{idsp})>0;
    }

    public Boolean deleteData(String idsp)
    {
        db = openHelper.getWritableDatabase();
        return db.delete(DatabaseHelper.TABLE,DatabaseHelper.COLUMN1+"=?",new String[]{idsp})>0;
    }
    public Boolean ClearData()
    {
        db = openHelper.getWritableDatabase();
        return db.delete(DatabaseHelper.TABLE,null,null)>0;
    }
}
