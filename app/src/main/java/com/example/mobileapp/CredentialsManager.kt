package com.example.mobileapp

object CredentialsManager {

    private val accounts = mutableMapOf<String, String>()

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun register(email: String, password: String): Boolean {
        if (accounts.containsKey(email)) {
            return false
        }
        accounts[email] = password
        return true
    }

    fun login(email: String, password: String): Boolean {
        return accounts[email] == password
    }
}
