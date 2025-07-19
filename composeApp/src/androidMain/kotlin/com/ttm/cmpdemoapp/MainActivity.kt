package com.ttm.cmpdemoapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.ttm.cmpdemoapp.auth.data.local.DatabaseDriverFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // For Android API 21+ (Lollipop and above)
        window.statusBarColor = android.graphics.Color.TRANSPARENT

        // For Android API 30+ (Android 11+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }

        setContent {
            val driver = DatabaseDriverFactory(this).create()
            App(driver)
        }
    }
}

