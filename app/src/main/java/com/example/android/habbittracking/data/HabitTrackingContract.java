package com.example.android.habbittracking.data;

import android.provider.BaseColumns;


public class HabitTrackingContract {

    //to prevent someone to accidentally initiate the contract class, use empty constructor
    private HabitTrackingContract() {
    }

    public static final class HabitTrackingEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_HABIT_TIME = "time";
        public final static String COLUMN_HABIT_HOW_LONG = "long";
    }

}
