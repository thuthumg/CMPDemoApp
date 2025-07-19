package com.ttm.cmpdemoapp.auth.domain

class RegisterUserUseCase(private val repo: AuthRepository) {
    operator fun invoke(email: String, password: String): Boolean {
        return repo.registerUser(email, password)
    }
}
