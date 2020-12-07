package com.example.pkm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context){
        super(context,"dbsampah",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table old(id varchar(50),berat varchar(50),jenis varchar(50))");
        db.execSQL("create table user (id varchar(50),email varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists old");
        db.execSQL("drop table if exists user");
    }
    public Boolean insertOld(String a,String b,String c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",a);
        contentValues.put("berat",b);
        contentValues.put("jenis",c);
        long ins = db.insert("old",null,contentValues);
        if(ins==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public String getIdOld(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id from old",null);
        String c = null;
        cursor.moveToLast();
        if(cursor.getCount()>0){
            c = cursor.getString(0);
            return c;
        }
        else {
            return c;
        }
    }
    public String getBeratOld(String a){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select berat from old where id=?",new String[]{a});
        String c = null;
        cursor.moveToLast();
        if(cursor.getCount()>0){
            c = cursor.getString(0);
            return c;
        }
        else {
            return c;
        }
    }
    public String getJenisOld(String a){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select jenis from old where id=?",new String[]{a});
        String c = null;
        cursor.moveToLast();
        if(cursor.getCount()>0){
            c = cursor.getString(0);
            return c;
        }
        else return c;
    }
    public Boolean checkOld(String a){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id from old where id=?",new String[]{a});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    public void delete_old(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from old");
    }
}
