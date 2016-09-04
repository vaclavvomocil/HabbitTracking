package com.example.android.habbittracking;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.habbittracking.data.HabitsDbHelper;
import com.example.android.habbittracking.data.HabitTrackingContract.HabitTrackingEntry;


public class MainActivity extends AppCompatActivity {

    private HabitsDbHelper mDbHelper;
    private String appName = "HabitTracker";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new HabitsDbHelper(this);
    }

    public void insertHabit() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitTrackingEntry.COLUMN_HABIT_NAME, "footbal");
        values.put(HabitTrackingEntry.COLUMN_HABIT_TIME, "1st Janurary 2015");
        values.put(HabitTrackingEntry.COLUMN_HABIT_HOW_LONG, 2);

        long newRowId = db.insert(HabitTrackingEntry.TABLE_NAME, null, values);
        Log.v(appName, "New row id " + newRowId);
    }

    public void readHabit() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
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
            finish();
        }

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndexOrThrow(HabitTrackingEntry.COLUMN_HABIT_NAME));
            Log.v(appName, "value = " + name);
            c.close();
        }
    }

    public void deleteHabit() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(HabitTrackingEntry.TABLE_NAME, null, null);
        Log.v(appName, "all rows deleted");
    }

    public void updateHabit() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitTrackingEntry.COLUMN_HABIT_NAME, "tennis");

        String selection = HabitTrackingEntry.COLUMN_HABIT_NAME + " LIKE ?";
        String[] selectionArgs = {"footbal"};

        int count = db.update(HabitTrackingEntry.TABLE_NAME, values, selection, selectionArgs);
        Log.v(appName, "updated rows " + count);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert:
                insertHabit();
                return true;
            case R.id.read:
                readHabit();
                return true;
            case R.id.delete:
                deleteHabit();
                return true;
            case R.id.update:
                updateHabit();
                return true;
            default:
                return false;
        }
    }
}
