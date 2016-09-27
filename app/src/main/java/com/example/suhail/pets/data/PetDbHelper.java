package com.example.suhail.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.suhail.pets.data.PetContract.PetEntry;

/**
 * Created by suhail on 24-09-2016.
 */
public class PetDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pets.db";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + PetEntry.TABLE_NAME + " ( "
            + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PetEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + PetEntry.COLUMN_BREED + " TEXT NOT NULL, "
            + PetEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
            + PetEntry.COLUMN_WEIGHT + " FLOAT NOT NULL "
            + ")";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PetEntry.TABLE_NAME;
    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
