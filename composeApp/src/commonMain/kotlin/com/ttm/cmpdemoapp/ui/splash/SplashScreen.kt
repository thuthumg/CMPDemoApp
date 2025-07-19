package com.ttm.cmpdemoapp.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmpdemoapp.composeapp.generated.resources.Res
import cmpdemoapp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize()) {
        Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.Center)
        )
     }
}