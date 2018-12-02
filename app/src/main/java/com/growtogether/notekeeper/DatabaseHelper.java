package com.growtogether.notekeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper  extends SQLiteOpenHelper {

    public final static String NOTE_TABLE = "tb_note";
    public final static String TABLE_COL_DATE = "tb_date";
    public final static String TABLE_COL_TITLE = "tb_title";
    public final static String TABLE_COL_NOTE = "tb_note";
    public final static String TABLE_COL_UID = "tb_uid";
    public final static String DATABASE_NAME = "DB_Note";
    public final static  int DATABASE_VERSION = 1;


    public final static String CREATE_TABLE = "CREATE TABLE " + NOTE_TABLE + "( " +
            TABLE_COL_TITLE + " TEXT NOT NULL, " +
            TABLE_COL_DATE + " TEXT NOT NULL, " +
            TABLE_COL_NOTE + " TEXT NOT NULL," +
            TABLE_COL_UID + " TEXT NOT NULL);" ;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
}
