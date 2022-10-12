package com.juancka.capitalmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.juancka.capitalmanager.db.OperationsContract.OperationsEntry;
import com.juancka.capitalmanager.model.Operation;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "manager.db";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create table
        sqLiteDatabase.execSQL("CREATE TABLE " + OperationsEntry.TABLE_NAME + "(" +
                OperationsEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                OperationsEntry.DATE + " TEXT NOT NULL," +
                OperationsEntry.OPERATION + " TEXT NOT NULL," +
                OperationsEntry.MONEY_UPDATE + " REAL NOT NULL," +
                OperationsEntry.ACTUAL_MONEY + " REAL NOT NULL," +
                OperationsEntry.DETAIL + " TEXT);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + OperationsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long saveTransaction(SQLiteDatabase sqld, Operation operation){
        return sqld.insert(OperationsEntry.TABLE_NAME, null, operation.toContentValue());
    }



}
