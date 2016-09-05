package com.example.android.habbittracking.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habbittracking.data.HabitTrackingContract.HabitTrackingEntry;

public class HabitsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSION = 1;
    private static Context myContext;
    private String appName = "HabitTracker";
    SQLiteDatabase db;

    public HabitsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitTrackingEntry.TABLE_NAME + " ("
                + HabitTrackingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitTrackingEntry.COLUMN_HABIT_HOW_LONG + " INTEGER NOT NULL, "
                + HabitTrackingEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitTrackingEntry.COLUMN_HABIT_TIME + " TEXT );";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This method is intentionally left unimplemented. We don't need to upgrade to the 1st version of the database.
    }

    public void deleteDatabase() {
        myContext.deleteDatabase(DATABASE_NAME);
    }

    public void insertHabit() {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitTrackingEntry.COLUMN_HABIT_NAME, "football");
        values.put(HabitTrackingEntry.COLUMN_HABIT_TIME, "1st Janurary 2015");
        values.put(HabitTrackingEntry.COLUMN_HABIT_HOW_LONG, 2);

        long newRowId = db.insert(HabitTrackingEntry.TABLE_NAME, null, values);
        Log.v(appName, "New row id " + newRowId);
    }

    public void readHabit() {
        db = this.getWritableDatabase();
        Cursor c = null;

        String[] projection = {
                HabitTrackingEntry._ID,
                HabitTrackingEntry.COLUMN_HABIT_NAME,
                HabitTrackingEntry.COLUMN_HABIT_TIME,
                HabitTrackingEntry.COLUMN_HABIT_HOW_LONG
        };

        String selection = HabitTrackingEntry.COLUMN_HABIT_NAME + " = ?";
        String[] selectionArgs = {"football"};
        String sortOrder = HabitTrackingEntry.COLUMN_HABIT_NAME + " DESC";

        try {
            c = db.query(
                    HabitTrackingEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );
        } catch (NullPointerException e) {
            Log.v(appName, "null pointer cursor exception");
        }

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndexOrThrow(HabitTrackingEntry.COLUMN_HABIT_NAME));
            Log.v(appName, "value = " + name);
            c.close();
        }
    }

    public void deleteHabit() {

        db = this.getWritableDatabase();
        db.delete(HabitTrackingEntry.TABLE_NAME, null, null);
        Log.v(appName, "all rows deleted");
    }

    public void updateHabit() {

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitTrackingEntry.COLUMN_HABIT_NAME, "tennis");

        String selection = HabitTrackingEntry.COLUMN_HABIT_NAME + " LIKE ?";
        String[] selectionArgs = {"footbal"};

        int count = db.update(HabitTrackingEntry.TABLE_NAME, values, selection, selectionArgs);
        Log.v(appName, "updated rows " + count);


    }

}
