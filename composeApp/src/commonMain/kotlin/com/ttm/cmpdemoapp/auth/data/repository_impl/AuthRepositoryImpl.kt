package com.ttm.cmpdemoapp.auth.data.repository_impl

import com.ttm.cmpdemoapp.AppDatabase
import com.ttm.cmpdemoapp.auth.domain.AuthRepository

class AuthRepositoryImpl(private val db: AppDatabase): AuthRepository{
    override fun registerUser(email: String, password: String): Boolean {
        val exists = db.appDatabaseQueries.selectByEmail(email).executeAsOneOrNull()
        if (exists != null) return false

        db.appDatabaseQueries.insertUser(email,password)
        return true

    }
    override fun loginUser(email: String, password: String): Boolean {
        val user = db.appDatabaseQueries.selectByEmail(email).executeAsOneOrNull()
        return user != null && user.password == password
    }

}