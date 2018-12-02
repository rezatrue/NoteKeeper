package com.growtogether.notekeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseOperation {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseOperation(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void addNote(Note note) {
        Log.i("notes", "addNote");
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TABLE_COL_DATE, note.getDate());
        values.put(DatabaseHelper.TABLE_COL_NOTE, note.getNote());
        values.put(DatabaseHelper.TABLE_COL_TITLE, note.getTitle());
        values.put(DatabaseHelper.TABLE_COL_UID, note.getUid());

        // Inserting Row
        sqLiteDatabase.insert(DatabaseHelper.NOTE_TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        sqLiteDatabase.close(); // Closing database connection
    }

    public int updateContact(Note note) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TABLE_COL_DATE, note.getDate());
        values.put(DatabaseHelper.TABLE_COL_NOTE, note.getNote());
        values.put(DatabaseHelper.TABLE_COL_TITLE, note.getTitle());
        values.put(DatabaseHelper.TABLE_COL_UID, note.getUid());

        // updating row
        return sqLiteDatabase.update(DatabaseHelper.NOTE_TABLE, values, DatabaseHelper.TABLE_COL_UID + " = ?",
                new String[] { String.valueOf(note.getUid()) });
    }


    public void deleteContact(Note note) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        sqLiteDatabase.delete(DatabaseHelper.NOTE_TABLE, DatabaseHelper.TABLE_COL_UID + " = ?",
                new String[] { String.valueOf(note.getUid()) });
        sqLiteDatabase.close();
    }


    public ArrayList<Note> getAllNotes() {
        Log.i("notes", "getAllNotes");

        ArrayList<Note> noteList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.NOTE_TABLE;

        sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setTitle((cursor.getString(0)));
                note.setDate((cursor.getString(1)));
                note.setNote((cursor.getString(2)));
                note.setUid((cursor.getString(3)));

                // Adding contact to list
                noteList.add(note);
            } while (cursor.moveToNext());
        }

        // return contact list
        return noteList;
    }



}
