package com.aleshchenkov.mylearningproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginUserName = findViewById<EditText>(R.id.loginUserName)
        val loginPassword = findViewById<EditText>(R.id.loginPassword)
        val autoLoginCheckBox = findViewById<CheckBox>(R.id.autoLoginCheckBox)
        val loginButtonView = findViewById<Button>(R.id.loginButton)

        if (getSharedCheckBox()){
            val contentIntent = Intent(this, ContentActivity::class.java)
            startActivity(contentIntent)
        }

        loginButtonView.setOnClickListener {
            val sharedUserData = getSharedUserData()
            val enteredUserData =
                SplashActivity.PersonalUserData(loginUserName.text.toString(), loginPassword.text.toString(), autoLoginCheckBox.isChecked)

            if (sharedUserData.userName != enteredUserData.userName
                || sharedUserData.userPassword != enteredUserData.userPassword
            ) {
                Toast.makeText(this@LoginActivity, "Введеные данные неверны!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            writeUserAutoLoginCheckBox(autoLoginCheckBox.isChecked)

            val contentIntent = Intent(this, ContentActivity::class.java)
            startActivity(contentIntent)
        }

    }
    fun writeUserAutoLoginCheckBox(autoLoginCheckBox: Boolean){
        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
        storage.edit().putBoolean("autoLoginCheckBox", autoLoginCheckBox).apply()
    }
    fun getSharedCheckBox(): Boolean {
        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
        return storage.getBoolean("autoLoginCheckBox", false)
    }
    fun getSharedUserData() : SplashActivity.PersonalUserData {
        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val userName = storage.getString("username", "").orEmpty()
        val userPassword = storage.getString("password", "").orEmpty()
        val autoLoginCheckBox = storage.getBoolean("autoLoginCheckBox", false)
        return SplashActivity.PersonalUserData(userName, userPassword, autoLoginCheckBox)
    }
}