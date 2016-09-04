package com.example.android.habbittracking.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habbittracking.data.HabitTrackingContract.HabitTrackingEntry;

public class HabitsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSION = 1;
    private static Context myContext;

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

    }

    public void deleteDatabase() {
        myContext.deleteDatabase(DATABASE_NAME);
    }


}
