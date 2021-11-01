package com.example.androidassignments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "Messages.db";
    final static String TABLE_NAME = "Messages";
    final static int VERSION_NUM = 1;
    final static String KEY_ID = "Key_ID";
    final static String KEY_MESSAGE = "Key_Message";

    final static String DATABASE_CREATE = "create table " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_MESSAGE + " TEXT NOT NULL)";
    final static String DATABASE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + i + " newVersion=" + i1);
        db.execSQL(DATABASE_DROP);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int i, int i1) {
        Log.i("ChatDatabaseHelper", "Calling onDowngrade, oldVersion=" + i + " newVersion=" + i1);
        db.execSQL(DATABASE_DROP);
        onCreate(db);
    }


}
