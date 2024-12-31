package com.example.mobileapp

import org.junit.Test
import org.junit.Assert.assertEquals


class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("invalidEmail.com")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenProperEmailFormat_thenReturnTrue() {
        val isEmailValid = credentialsManager.isEmailValid("user@example.com")
        assertEquals(true, isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertEquals(false, isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val isPasswordValid = credentialsManager.isPasswordValid("mypassword123")
        assertEquals(true, isPasswordValid)
    }
}
