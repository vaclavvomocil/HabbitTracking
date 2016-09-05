package com.example.android.habbittracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.android.habbittracking.data.HabitsDbHelper;


public class MainActivity extends AppCompatActivity {

    HabitsDbHelper mDbHelper = new HabitsDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                mDbHelper.insertHabit();
                return true;
            case R.id.read:
                mDbHelper.readHabit();
                return true;
            case R.id.delete:
                mDbHelper.deleteHabit();
                return true;
            case R.id.update:
                mDbHelper.updateHabit();
                return true;
            default:
                return false;
        }
    }
}
