package com.ttm.cmpdemoapp.auth.presentation

sealed class AuthEvent{
    data class EmailChanged(val value: String): AuthEvent()
    data class PasswordChanged(val value: String): AuthEvent()

    object RegisterClicked: AuthEvent()
    object LoginClicked: AuthEvent()
}