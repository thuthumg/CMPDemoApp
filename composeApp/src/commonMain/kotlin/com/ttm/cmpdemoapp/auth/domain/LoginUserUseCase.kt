package com.ttm.cmpdemoapp.auth.domain

class LoginUserUseCase(private val repo: AuthRepository) {
    operator fun invoke(email: String, password: String): Boolean {
        return repo.loginUser(email, password)
    }
}