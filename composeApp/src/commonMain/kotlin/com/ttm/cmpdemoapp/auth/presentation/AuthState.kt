package com.ttm.cmpdemoapp.auth.presentation

data class AuthState(
    val email: String = "",
    val password: String = "",
    var isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)