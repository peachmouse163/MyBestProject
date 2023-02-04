package com.example.mybestproject.shared;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mydb extends SQLiteOpenHelper {

    static Mydb mydb;

    public static Mydb getMydb(Context context){
        if (mydb == null){
            mydb = new Mydb(context);
        }
        return mydb;
    }

    public Mydb(@Nullable Context context) {
        super(context, "pickme.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //创建数据表
        String sql1 = "create table things(id integer primary key,tname text,tnum integer,tmoney integer)";

        sqLiteDatabase.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //自定义插入数据方法
    public void insert(String tname,int tnum,int tmoney){

        ContentValues cv = new ContentValues();
        cv.put("tname",tname);
        cv.put("tnum",tnum);
        cv.put("tmoney",tmoney);
        getReadableDatabase().insert("things","tname",cv);

    }
    //自定义查询数据
    public Cursor select(){
        String sql = "select * from things";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        return c;
    }
    public Cursor select(String word){
        String sql = "select * from things where tname='"+word+"'";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        return c;
    }
    public Cursor selectmoney(){
        String sql = "select * from things where tnum!=0";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        return c;
    }
    //自定义每个字段上的值的方法
    public String getTName(Cursor cursor){
        return cursor.getString(1);
    }
    public String getTNum(Cursor cursor){
        return cursor.getString(2);
    }
    public String getTMoney(Cursor cursor){
        return cursor.getString(3);
    }

    //自定义更改数据
    public void updata(String tname,int tnum,int tmoney){
        ContentValues cv = new ContentValues();
        cv.put("tname",tname);
        cv.put("tnum",tnum);
        cv.put("tmoney",tmoney);
        getReadableDatabase().update("things",cv,"tname=?",new String[]{String.valueOf(tname)});
    }

    //自定义删除数据
    public void delete(String name){
        getReadableDatabase().delete("things","tname=?",new String[]{String.valueOf(name)});
    }
    //
    public void close(){
        if (mydb !=null){
            getReadableDatabase().close();
        }
    }
}
