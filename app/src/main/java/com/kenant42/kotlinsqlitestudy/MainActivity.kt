package com.kenant42.kotlinsqlitestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kenant42.kotlinsqlitestudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //UserDAO() sınıfı üzerinden tüm metotlara erişebilirsiniz

        val vt = CustomDatabaseHelper(this)
        UserDAO().saveUser(vt, "Ahmet", "35435345", 18)

        val userList = UserDAO().getUsers(vt)
        for(user in userList){
            Log.e("USER NO ",user.user_id.toString())
            Log.e("USER NAME ",user.user_name)
            Log.e("USER AGE ",user.user_age.toString())
            Log.e("USER PHONE ",user.user_phone)

        }
    }
}