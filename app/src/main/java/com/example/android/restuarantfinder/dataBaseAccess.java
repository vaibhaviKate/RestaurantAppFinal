package com.example.android.restuarantfinder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class dataBaseAccess {
    public static int counter=12;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static dataBaseAccess instance;
    public static int itemNumber=6;


    //object creation
    private dataBaseAccess(Context context){
        this.openHelper = new dataBaseOpenHelper(context);
    }

    //return instance of the database
    public static dataBaseAccess getInstance(Context context){
        if(instance == null){
            instance = new dataBaseAccess(context);
        }
        return instance;
    }

    //to open database
    public void open(){
        this.db = openHelper.getReadableDatabase();


    }

    public void close(){
        if(db != null){
            this.db.close();
        }
    }

    //create method to query and return result

    public String getRestName(String name) {

        String[] selectionArgs = {name};
        Cursor cursor = db.rawQuery("select * from restDetails where restName like ?", selectionArgs);

        String rname = "";
        if(cursor.moveToFirst()){
            rname = cursor.getString(1);
            Log.d("name of resto",rname);
        }
        cursor.close();

        return rname;

    }

    public ArrayList<allMenuMao> getStartersMao(String itemType){
        ArrayList<allMenuMao> list = new ArrayList<>();
        String[] selectionArgs = {itemType};
        allMenuMao menuMao;
        Cursor cursor = db.rawQuery("select * from menuMao where itemType like ?", selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            menuMao = new allMenuMao(cursor.getString(2), cursor.getInt(3));
            list.add(menuMao);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public void openForWriting(){
        this.db = openHelper.getWritableDatabase();
    }

    public ArrayList<allMenuMao> getDrinksMao(String itemType){
        ArrayList<allMenuMao> list = new ArrayList<>();
        allMenuMao menuMao;
        String[] selectionArgs = {itemType};
        Cursor cursor = db.rawQuery("select * from menuMao where itemType like ?", selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            menuMao = new allMenuMao(cursor.getString(2), cursor.getInt(3));
            list.add(menuMao);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<allMenuMao> getAllMenuMao(){
        ArrayList<allMenuMao> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from menuMao", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new allMenuMao(cursor.getString(2), cursor.getInt(3)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void insert(String restname,String location)
    {
        String str = "INSERT INTO restDetails(restName,restAddress) VALUES(?,?)";
        SQLiteStatement stmt = db.compileStatement(str);
        //stmt.bindLong(1, counter++);
        stmt.bindString(1,restname);
        stmt.bindString(2, location);
        long i = stmt.executeInsert();
    }
    public void insertItem(String itemtype,String itemName,int itemPrice)
    {
        String str = "INSERT INTO menuMao(itemType,itemName,itemPrice) VALUES(?,?,?)";
        SQLiteStatement stmt = db.compileStatement(str);
        //stmt.bindLong(1, itemNumber++);
        stmt.bindString(1,itemtype);
        stmt.bindString(2, itemName);
        stmt.bindLong(3, itemPrice);
        long i = stmt.executeInsert();
    }
    public void deleteItem(String itemName)
    {
        String str = "DELETE FROM menuMao WHERE itemName=?";
        SQLiteStatement stmt = db.compileStatement(str);
        stmt.bindString(1, itemName);
        long j = stmt.executeUpdateDelete();
    }
    public void updateItem(int itemPrice,String itemName)
    {
        String str = "UPDATE menuMao SET itemPrice=? WHERE itemName=?";
        SQLiteStatement stmt = db.compileStatement(str);
        stmt.bindLong(1, itemPrice);
        stmt.bindString(2, itemName);
        long j = stmt.executeUpdateDelete();
    }



}


