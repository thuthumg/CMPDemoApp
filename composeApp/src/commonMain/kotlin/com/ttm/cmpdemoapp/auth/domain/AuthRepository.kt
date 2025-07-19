package com.ttm.cmpdemoapp.auth.domain

interface AuthRepository {
    fun registerUser(email: String, password: String): Boolean
    fun loginUser(email: String, password: String): Boolean

}
