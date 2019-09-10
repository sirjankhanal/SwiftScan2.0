package com.example.swiftscan;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DBHelper {

    private static DBHelper dbHelper;
    private SQLiteDatabase db;
    SharedPreferences mySharedPreferences;

    private DBHelper(Context context) {
        SQHelper dbHelper = new SQHelper(context, "drink_base", null, 1);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
        mySharedPreferences = context.getSharedPreferences("drinker_data", Context.MODE_PRIVATE);
    }

    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    public Drink getProduct(String key_code) {
        //return Drink.getSample();

        Cursor cursor = db.query("PRODUCTS",
                null, "CODE = ?", new String[]{key_code}, null, null, null);
        cursor.moveToFirst();
        Drink s = Drink.getSample();
        s.setABV(cursor.getString(cursor.getColumnIndex("ABV")));
        s.setName(cursor.getString(cursor.getColumnIndex("NAME")));
        s.setBrand(cursor.getString(cursor.getColumnIndex("BRAND")));
        s.setPrice(cursor.getString(cursor.getColumnIndex("PRICE")));
        s.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
        s.setCountry(cursor.getString(cursor.getColumnIndex("COUNTRY")));
        s.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
        s.setCategory(cursor.getString(cursor.getColumnIndex("CATEGORY")));
        s.setCode(cursor.getString(cursor.getColumnIndex("CODE")));
        if (s.getCode().equals("000")) {
            return null;
        }
        return s;
    }


    public void saveProduct(String key_code) {
        db.execSQL("INSERT INTO SAVED (NAME, CODE) VALUES('" + getName() + "','" + key_code + "');");
    }

    public List<Drink> getSaved() {
        HashSet<String> ls1 = new HashSet<>();
        Cursor cursor0 = db.query("SAVED",
                null, "NAME = ?", new String[]{getName()}, null, null, null);
        while (cursor0.moveToNext()) {
            ls1.add(cursor0.getString(cursor0.getColumnIndex("CODE")));
        }
        cursor0.close();
        String[] item = ls1.toArray(new String[ls1.size()]);
        ArrayList<Drink> drinks = new ArrayList<>();
        for (String s2 : item) {
            Cursor cursor = db.query("PRODUCTS",
                    null, "CODE = ?", new String[]{s2}, null, null, null);
            while (cursor.moveToNext()) {
                Drink s = Drink.getSample();
                s.setABV(cursor.getString(cursor.getColumnIndex("ABV")));
                s.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                s.setBrand(cursor.getString(cursor.getColumnIndex("BRAND")));
                s.setPrice(cursor.getString(cursor.getColumnIndex("PRICE")));
                s.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
                s.setCountry(cursor.getString(cursor.getColumnIndex("COUNTRY")));
                s.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
                s.setCategory(cursor.getString(cursor.getColumnIndex("CATEGORY")));
                s.setCode(cursor.getString(cursor.getColumnIndex("CODE")));
                drinks.add(s);
            }
        }
        return drinks;
    }

    public void setName(String name) {

        SharedPreferences.Editor ed = mySharedPreferences.edit();
        ed.putString("name", name);
        ed.apply();
    }

    public String getName() {

        return mySharedPreferences.getString("name", "none");
    }

    public void addUser(String login, String pass) {
        db.execSQL("INSERT INTO USERS (NAME, PASS) VALUES('" + login + "','" + pass + "');");
    }

    public boolean checkUser(String login, String pass) {
        Cursor cursor0 = db.query("USERS",
                null, "NAME = ?", new String[]{login}, null, null, null);
        cursor0.moveToFirst();
        String p = cursor0.getString(cursor0.getColumnIndex("PASS"));
        return pass.equals(p);
    }

    public void addDrink(Context context, String name, String toString1, String toString2,
                         String toString3, String toString4, String toString5, String toString6,
                         String toString7, String toString8, String toString9, String toString10, String toString11) {
        String newDrink = "'" + name + "','" + toString1 + "','" + toString2 + "','" + toString3 +
                "','" + toString4 + "','" + toString5 + "','" + toString6 + "','" + toString7 +
                "','" + toString8 + "','" + toString9 + "','" + toString10 + "','" + toString11 + "'";
        db.execSQL("INSERT INTO PRODUCTS (NAME, BRAND, PRICE, DESCRIPTION, ABV, COUNTRY, TYPE, " +
                "CATEGORY, CODE, VOLUME,QUANTITY,ALPERCENTES) VALUES(" + newDrink + ");");
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "New drinks");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, "new_drinks.csv");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(newDrink);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
