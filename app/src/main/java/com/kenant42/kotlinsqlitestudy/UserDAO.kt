package com.kenant42.kotlinsqlitestudy

import android.annotation.SuppressLint
import android.content.ContentValues

class UserDAO {

    fun saveUser(vt: CustomDatabaseHelper, user_name: String, user_phone: String, user_age: Int) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("user_name", user_name)
        values.put("user_phone", user_phone)
        values.put("user_age", user_age)

        db.insertOrThrow("users", null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getUsers(vt: CustomDatabaseHelper): ArrayList<User> {
        val users = ArrayList<User>()
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM users", null)

        while (cursor.moveToNext()) {
            val user = User(
                cursor.getInt(cursor.getColumnIndex("user_id")),
                cursor.getString(cursor.getColumnIndex("user_name")),
                cursor.getString(cursor.getColumnIndex("user_phone")),
                cursor.getInt(cursor.getColumnIndex("user_age"))

            )

            users.add(user)
        }
        return users
    }


    fun updateUser(
        vt: CustomDatabaseHelper,
        user_id: Int,
        user_name: String,
        user_phone: String,
        user_age: Int
    ) {
        val dbx = vt.writableDatabase
        val values = ContentValues()
        values.put("user_name", user_name)
        values.put("user_phone", user_phone)
        values.put("user_age", user_age)

        dbx.update("users", values, "user_id=?", arrayOf(user_id.toString()))
        dbx.close()
    }

    fun removeUser(vt: CustomDatabaseHelper, user_id: Int) {
        val dbx = vt.writableDatabase
        dbx.delete("users", "user_id=?", arrayOf(user_id.toString()))
        dbx.close()
    }


    @SuppressLint("Range")
    fun search(vt:CustomDatabaseHelper, keyWord:String):ArrayList<User>{
        val users = ArrayList<User>()
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE user_name LIKE '%$keyWord%'", null)

        while (cursor.moveToNext()) {
            val user = User(
                cursor.getInt(cursor.getColumnIndex("user_id")),
                cursor.getString(cursor.getColumnIndex("user_name")),
                cursor.getString(cursor.getColumnIndex("user_phone")),
                cursor.getInt(cursor.getColumnIndex("user_age"))

            )

            users.add(user)
        }
        return users
    }


    @SuppressLint("Range")
    fun fetchRandomDatas(vt:CustomDatabaseHelper, keyWord:String):ArrayList<User>{
        val users = ArrayList<User>()
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM users ORDER BY RANDOM() LIMIT 5", null)

        while (cursor.moveToNext()) {
            val user = User(
                cursor.getInt(cursor.getColumnIndex("user_id")),
                cursor.getString(cursor.getColumnIndex("user_name")),
                cursor.getString(cursor.getColumnIndex("user_phone")),
                cursor.getInt(cursor.getColumnIndex("user_age"))

            )

            users.add(user)
        }
        return users
    }

    @SuppressLint("Range")
    fun checkRecord(vt:CustomDatabaseHelper, keyWord:String):Int{
        var result = 0
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) AS result FROM users WHERE user_name '%$keyWord%'", null)

        while (cursor.moveToNext()) {
             result =   cursor.getInt(cursor.getColumnIndex("result"))
        }
        return result
    }


    @SuppressLint("Range")
    fun fetchUser(vt:CustomDatabaseHelper, user_id:Int): User? {
        var user:User? = null
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE user_id=$user_id", null)

        while (cursor.moveToNext()) {
             user = User(
                cursor.getInt(cursor.getColumnIndex("user_id")),
                cursor.getString(cursor.getColumnIndex("user_name")),
                cursor.getString(cursor.getColumnIndex("user_phone")),
                cursor.getInt(cursor.getColumnIndex("user_age"))
            )
        }
        return user
    }
}