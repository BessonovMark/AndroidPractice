package com.aleshchenkov.mylearningproject

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.service.autofill.UserData
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val activeRegButtonColor = "#fbe7e1"
        val nonActiveRegButtonColor = "#FFFFFFFF"
        val regByNumberHint = "Введите телефон"
        val regByEmailHint = "Введите Email"
        val minPasswordSize = 8

        val regButton = findViewById<Button>(R.id.regButton)
        val regByNumberButton = findViewById<Button>(R.id.regByNumberButton)
        val regByEmailButton = findViewById<Button>(R.id.regByEmailButton)
        val regNameView = findViewById<EditText>(R.id.regNameView)
        val regPasswordView = findViewById<EditText>(R.id.regPasswordView)
        val regRepeatPasswordView = findViewById<EditText>(R.id.regRepeatPasswordView)

        val incorrectEmailToast = Toast.makeText(this@RegistrationActivity, "Email введен некоррекно!", Toast.LENGTH_SHORT)
        val incorrectPhoneNumberToast = Toast.makeText(this@RegistrationActivity, "Телефон введен некоррекно!", Toast.LENGTH_SHORT)
        val smallPasswordToast = Toast.makeText(this@RegistrationActivity, "Пароль слишком короткий!", Toast.LENGTH_SHORT)
        val passwordsNotRepeatedToast = Toast.makeText(this@RegistrationActivity, "Пароли не совпадают!", Toast.LENGTH_SHORT)
        val passwordEmptyToast = Toast.makeText(this@RegistrationActivity, "Пожалуйста, введите пароль!", Toast.LENGTH_SHORT)

        var isRegByEmail = true
        regByNumberButton.setOnClickListener{
            regByNumberButton.setBackgroundColor(Color.parseColor(activeRegButtonColor))
            regByEmailButton.setBackgroundColor(Color.parseColor(nonActiveRegButtonColor))
            regNameView.hint = regByNumberHint
            regNameView.inputType = InputType.TYPE_CLASS_PHONE
            isRegByEmail = false
        }
        regByEmailButton.setOnClickListener{
            regByNumberButton.setBackgroundColor(Color.parseColor(nonActiveRegButtonColor))
            regByEmailButton.setBackgroundColor(Color.parseColor(activeRegButtonColor))
            regNameView.hint = regByEmailHint
            regNameView.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            isRegByEmail = true
        }
        regButton.setOnClickListener {
            val regName = regNameView.text.toString()
            val regPassword = regPasswordView.text.toString()
            val regRepeatPassword = regRepeatPasswordView.text.toString()
            if (isRegByEmail) {
                if (!regName.contains('@')) {
                    incorrectEmailToast.show()
                    return@setOnClickListener
                }
            }
            else if (regName.indexOf('+') != 0){
                incorrectPhoneNumberToast.show()
                return@setOnClickListener
            }

            if (regPassword.isEmpty()){
                passwordEmptyToast.show()
                return@setOnClickListener
            }
            if (regPassword.length < minPasswordSize){
                smallPasswordToast.show()
                return@setOnClickListener
            }
            if (regPassword != regRepeatPassword){
                passwordsNotRepeatedToast.show()
                return@setOnClickListener
            }

            writeUserData(SplashActivity.PersonalUserData(regName, regPassword))
            val contentIntent = Intent(this, ContentActivity::class.java)
            startActivity(contentIntent)


        }

    }

    fun writeUserData(userData: SplashActivity.PersonalUserData){
        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
        storage.edit().putString("username", userData.userName).apply()
        storage.edit().putString("password", userData.userPassword).apply()
        storage.edit().putBoolean("autoLoginCheckBox", false).apply()
    }
}
