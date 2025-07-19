package com.ttm.cmpdemoapp

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ttm.cmpdemoapp.auth.data.repository_impl.AuthRepositoryImpl
import com.ttm.cmpdemoapp.auth.domain.LoginUserUseCase
import com.ttm.cmpdemoapp.auth.domain.RegisterUserUseCase
import com.ttm.cmpdemoapp.auth.presentation.AuthScreen
import com.ttm.cmpdemoapp.auth.presentation.AuthViewModel
import com.ttm.cmpdemoapp.core.presentation.Blue009FD9
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.ttm.cmpdemoapp.core.presentation.theme.DemoAppTheme
import com.ttm.cmpdemoapp.utils.SetStatusBarColor
import app.cash.sqldelight.db.SqlDriver
import com.ttm.cmpdemoapp.ui.home.HomeRoute
import com.ttm.cmpdemoapp.ui.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
@Preview
fun App(driver: SqlDriver) {

    val db = remember { AppDatabase(driver) }
    val repository = remember { AuthRepositoryImpl(db) }
    val loginUseCase = remember { LoginUserUseCase(repository) }
    val registerUseCase = remember { RegisterUserUseCase(repository) }
    val viewModel = remember { AuthViewModel(registerUseCase, loginUseCase) }

    var startDestination: AppRoute by remember { mutableStateOf(AppRoute.Splash) }
    val navController = rememberNavController()


    DemoAppTheme {
        SetStatusBarColor(statusBarColor = Blue009FD9, navBarColor = Color.Black)

        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<AppRoute.Splash> {
                SplashScreen()
            }

            composable<AppRoute.AuthGraph>(
                enterTransition = { enterTransition },
                popEnterTransition = { popEnterTransition },
                popExitTransition = { popExitTransition }
            ) {
                AuthScreen(
                    modifier = Modifier
                        .safeContentPadding()
                        .fillMaxSize()
                        .systemBarsPadding(),
                    viewModel = viewModel,
                    onNavToHomeTriggered = {
                        startDestination = AppRoute.Home
                    },
                )
            }

            composable<AppRoute.Home>(
                enterTransition = { enterTransition },
                popEnterTransition = { popEnterTransition },
                popExitTransition = { popExitTransition }
            ) {
                HomeRoute()
            }
        }

        LaunchedEffect(Unit) {
            delay(2000)
            navController.navigate(AppRoute.AuthGraph) {
                popUpTo(AppRoute.Splash) { inclusive = true }
            }

        }
    }
}

// Transitions for navigation
val enterTransition = slideInHorizontally(initialOffsetX = { it / 2 })
val popEnterTransition = slideInHorizontally(initialOffsetX = { -it / 4 })
val popExitTransition = slideOutHorizontally(targetOffsetX = { it })
