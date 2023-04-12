package com.aleshchenkov.mylearningproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val data = getSharedUserData()

        if (data.userName.isEmpty() || data.userPassword.isEmpty()){
            val regIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(regIntent)
        } else if (data.autoCheckBox){
            val contentIntent = Intent(this, ContentActivity::class.java)
            startActivity(contentIntent)
        }
        else{
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    fun getSharedUserData() : PersonalUserData {
        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val userName = storage.getString("username", "").orEmpty()
        val userPassword = storage.getString("password", "").orEmpty()
        val autoLoginCheckBox = storage.getBoolean("autoLoginCheckBox", false)
        return PersonalUserData(userName, userPassword, autoLoginCheckBox)
    }

    data class PersonalUserData(
        val userName: String,
        val userPassword: String,
        val autoCheckBox: Boolean = false
    )
}