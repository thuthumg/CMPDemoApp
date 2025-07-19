package com.ttm.cmpdemoapp.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable



@Composable
fun DemoAppTheme(content: @Composable () -> Unit) {
    val typography = CustomTypography()

    MaterialTheme(
        typography = typography,
        content = content
    )
}

