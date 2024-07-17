package com.kenant42.kotlinsqlitestudy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CustomDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "userstable", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE users (user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_name TEXT,user_phone TEXT,user_age INTEGER);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }
}