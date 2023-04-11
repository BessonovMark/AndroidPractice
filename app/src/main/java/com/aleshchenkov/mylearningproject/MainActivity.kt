package com.aleshchenkov.mylearningproject

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
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

        val incorrectEmailToast = Toast.makeText(this@MainActivity, "Email введен некоррекно!", Toast.LENGTH_SHORT)
        val incorrectPhoneNumberToast = Toast.makeText(this@MainActivity, "Телефон введен некоррекно!", Toast.LENGTH_SHORT)
        val smallPasswordToast = Toast.makeText(this@MainActivity, "Пароль слишком короткий!", Toast.LENGTH_SHORT)
        val passwordsNotRepeatedToast = Toast.makeText(this@MainActivity, "Пароли не совпадают!", Toast.LENGTH_SHORT)
        val passwordEmptyToast = Toast.makeText(this@MainActivity, "Пожалуйста, введите пароль!", Toast.LENGTH_SHORT)

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
            if (isRegByEmail) {
                if (!regNameView.text.toString().contains('@')) {
                    incorrectEmailToast.show()
                }
            }
            else if (regNameView.text.toString().indexOf('+') != 0){
                incorrectPhoneNumberToast.show()
            }

            if (regPasswordView.text.toString().isEmpty()){
                passwordEmptyToast.show()
            }
            else if (regPasswordView.text.toString().length < minPasswordSize){
                smallPasswordToast.show()
            }
            else if (regPasswordView.text.toString() != regRepeatPasswordView.text.toString()){
                passwordsNotRepeatedToast.show()
            }
            else{
                Toast.makeText(this@MainActivity, "Все прошло :(!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
