package com.ttm.cmpdemoapp.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cmpdemoapp.composeapp.generated.resources.Res
import cmpdemoapp.composeapp.generated.resources.compose_multiplatform
import cmpdemoapp.composeapp.generated.resources.ic_language
import org.jetbrains.compose.resources.painterResource

@Composable
fun AuthScreen(
    modifier: Modifier,
    viewModel: AuthViewModel,
    onNavToHomeTriggered: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    var localErrorMessage by remember { mutableStateOf<String?>(null) }
    var isLogin by remember { mutableStateOf(true) }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = modifier.padding(24.dp)
    ) {
        LanguageIcon(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(24.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DemoAppIcon()
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isLogin) "Login" else "Register",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(AuthEvent.EmailChanged(it))
                    localErrorMessage = null
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(AuthEvent.PasswordChanged(it))
                    localErrorMessage = null
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (!isLogin) {
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        localErrorMessage = null
                    },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            (localErrorMessage ?: state.error)?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Text(it, color = Color.Red)
            }

            if (state.isSuccess) {
                Spacer(modifier = Modifier.height(10.dp))
                //Text("Success!", color = Color.Green)
                onNavToHomeTriggered()
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    localErrorMessage = when {
                        state.email.isBlank() -> "Email cannot be empty"
                        state.password.isBlank() -> "Password cannot be empty"
                        !isLogin && confirmPassword.isBlank() -> "Confirm Password cannot be empty"
                        !isLogin && state.password != confirmPassword -> "Passwords do not match"
                        else -> null
                    }

                    if (localErrorMessage == null) {
                        if (isLogin) viewModel.onEvent(AuthEvent.LoginClicked)
                        else viewModel.onEvent(AuthEvent.RegisterClicked)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isLogin) "Login" else "Register")
            }

//            Button(
//                onClick = {
//                    when {
//                        state.email.isBlank() || state.password.isBlank() -> {
//                            viewModel.onEvent(AuthEvent.EmailChanged(state.email)) // trigger to refresh
//                            viewModel.onEvent(AuthEvent.PasswordChanged(state.password))
//                        }
//
//                        !isLogin && state.password != confirmPassword -> {
//                            // handle password mismatch error manually
//                            // you'd want to store confirmPassword in state too if using fully MVI
//                        }
//
//                        isLogin -> viewModel.onEvent(AuthEvent.LoginClicked)
//                        else -> viewModel.onEvent(AuthEvent.RegisterClicked)
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(if (isLogin) "Login" else "Register")
//            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = {
                isLogin = !isLogin
                confirmPassword = ""
                localErrorMessage = null
            }) {
                Text(if (isLogin) "Don't have an account? Register" else "Already have an account? Login")
            }
        }
    }
}

@Composable
fun DemoAppIcon() {
    Image(
        painter = painterResource(Res.drawable.compose_multiplatform),
        contentDescription = null,
        modifier = Modifier.size(150.dp)
    )
}


@Composable
fun LanguageIcon(modifier: Modifier) {
    Image(
        painter = painterResource(Res.drawable.ic_language),
        contentDescription = "Language Icon",
        modifier = modifier
    )

}