package com.ttm.cmpdemoapp.auth.presentation

import androidx.lifecycle.ViewModel
import com.ttm.cmpdemoapp.auth.domain.LoginUserUseCase
import com.ttm.cmpdemoapp.auth.domain.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel (
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
): ViewModel(){

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EmailChanged -> _state.update{
                it.copy(email = event.value)
            }

            is AuthEvent.PasswordChanged -> _state.update {
                it.copy(password = event.value)
            }


            AuthEvent.RegisterClicked -> register()
            AuthEvent.LoginClicked -> login()
        }
    }

    private fun register(){
        val current = _state.value
        _state.update { it.copy(isLoading = true, error = null ) }

        val success = registerUserUseCase(current.email, current.password)
        _state.update {
            it.copy(
                isLoading = false,
                isSuccess =  success,
                error = if(success) null else "User already exists"
            )
        }
    }

    private fun login(){
        val current  = _state.value
        _state.update { it.copy(isLoading = true, error = null) }

        val success = loginUserUseCase(current.email, current.password)
        _state.update {
            it.copy(
                isLoading = false,
                isSuccess = success,
                error = if(success) null else "Invalid credentials"
            )
        }
    }
}