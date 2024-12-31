package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {

    private val emailInput: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.emailCreateTextInputLayout) }
    private val passwordInput: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.passwordCreateTextInputLayout) }
    private val createAccountButton: Button by lazy { findViewById<Button>(R.id.createAccountButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        createAccountButton.setOnClickListener {
            handleRegistration()
        }
    }

    private fun handleRegistration() {
        val email = emailInput.editText?.text.toString()
        val password = passwordInput.editText?.text.toString()

        var isValid = true

        if (!CredentialsManager.isEmailValid(email)) {
            emailInput.error = "Invalid email address"
            isValid = false
        } else {
            emailInput.error = null
        }

        if (!CredentialsManager.isPasswordValid(password)) {
            passwordInput.error = "Password cannot be empty"
            isValid = false
        } else {
            passwordInput.error = null
        }

        if (isValid) {
            val isRegistered = CredentialsManager.register(email, password)
            if (isRegistered) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                emailInput.error = "Email is already taken"
            }
        }
    }
}
