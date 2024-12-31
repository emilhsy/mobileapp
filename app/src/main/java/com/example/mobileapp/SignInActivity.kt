package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {

    private val emailInput: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.emailTextInputLayout) }
    private val passwordInput: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.passwordTextInputLayout) }
    private val signInButton: Button by lazy { findViewById<Button>(R.id.signInButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton.setOnClickListener {
            validateAndLogin()
        }
    }

    private fun validateAndLogin() {
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
            if (CredentialsManager.login(email, password)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                emailInput.error = "Invalid credentials"
                passwordInput.error = "Invalid credentials"
            }
        }
    }
}
