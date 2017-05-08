package com.example.user.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Paula on 2017-05-08.
 */

public class PositionDAO extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static final String DATABASE_NAME = "PositionDAO";//Database name
    public static String TABLE_NAME = "POSITIONS";//Table name
    public static final int DATABASE_VERSION = 2;//Database version
    //Position Table Columns name
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";

    public PositionDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

        @Override
    public void onCreate(SQLiteDatabase db) {
            //create the table
            String sql = "Create TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_NAME + " TEXT," + KEY_LATITUDE + " TEXT," + KEY_LONGITUDE + " TEXT," + ")";
            db.execSQL(sql);
        }
    //Adding new Position
    public void insert(Position position) {

        db = this.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put(KEY_NAME, position.getName());
        data.put(KEY_LATITUDE, position.getLatitude());
        data.put(KEY_LONGITUDE, position.getLongitude());

        //Inserting row
        db.insert(TABLE_NAME, null, data);
        db.close(); //Closing database coonection
        System.out.println(position);

    }

    public ArrayList<Position> getlistAll() {

        ArrayList<Position> positions = new ArrayList<Position>();
        //Select All Query
        db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Position position = new Position();
                position.setId(Integer.parseInt(cursor.getString(0)));
                position.setName(cursor.getString(1));
                position.setLatitude(cursor.getString(2));
                position.setLongitude(cursor.getString(3));

                positions.add(position);
            } while (cursor.moveToNext());
        }
        db.close(); // close inserting data from database
        return positions; // return position
    }

    public void remove(Position position){
        SQLiteDatabase database = getWritableDatabase();
        String[] params = {position.getId() + ""};
        database.delete("positions", "id = ?", params);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
