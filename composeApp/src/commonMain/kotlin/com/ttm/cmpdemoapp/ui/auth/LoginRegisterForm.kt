package com.ttm.cmpdemoapp.ui.auth

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
fun LoginRegisterScreen(modifier: Modifier) {
    var isLogin by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier.padding(24.dp)
    ) {

        LanguageIcon(modifier = Modifier
            .align(Alignment.TopStart)
            .size(24.dp))

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Centered App Icon (optional)
            DemoAppIcon()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isLogin) "Login" else "Register",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (!isLogin) {
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            errorMessage?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Text(it, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    errorMessage = when {
                        email.isBlank() || password.isBlank() -> "Email and password cannot be empty"
                        !isLogin && password != confirmPassword -> "Passwords do not match"
                        else -> {
                            // Success case (simulate login/register)
                            if (isLogin) "Logged in!" else "Registered!"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isLogin) "Login" else "Register")
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = {
                isLogin = !isLogin
                errorMessage = null
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