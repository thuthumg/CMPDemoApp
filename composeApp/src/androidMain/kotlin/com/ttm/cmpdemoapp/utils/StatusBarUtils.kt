package com.ttm.cmpdemoapp.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

@Composable
actual fun SetStatusBarColor(statusBarColor: Color,navBarColor: Color){

    val context = LocalContext.current
    val window = (context as? Activity)?.window ?: return
    SideEffect {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            WindowCompat.setDecorFitsSystemWindows(window, false)

            val decorView = window.decorView as ViewGroup

            // -- STATUS BAR VIEW SETUP --
            var statusBarView = decorView.findViewWithTag<View>("customStatusBarView")
            if (statusBarView == null) {
                statusBarView = View(window.context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        0
                    )
                    tag = "customStatusBarView"
                    setBackgroundColor(statusBarColor.toArgb())
                }
                decorView.addView(statusBarView)
            }

            // -- NAVIGATION BAR VIEW SETUP --
            var navBarView = decorView.findViewWithTag<View>("customNavBarView")
            if (navBarView == null) {
                navBarView = View(window.context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        0
                    )
                    tag = "customNavBarView"
                    setBackgroundColor(navBarColor.toArgb())
                }
                decorView.addView(navBarView)
            }

            ViewCompat.setOnApplyWindowInsetsListener(decorView) { _, insets ->
                val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
                val isImeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())

                // Set status bar height
                statusBarView.layoutParams.height = systemInsets.top
                statusBarView.requestLayout()

                // Set navigation bar height
                navBarView.layoutParams.height = systemInsets.bottom
                navBarView.requestLayout()

                // Position nav bar view at bottom
                navBarView.translationY = (decorView.height - systemInsets.bottom).toFloat()

                // Apply padding to main content (excluding BottomAppBar etc.)
                val contentView = decorView.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
                contentView?.setPadding(
                    0,
                    systemInsets.top,
                    0,
                    if (isImeVisible) imeInsets.bottom else systemInsets.bottom
                )

                WindowInsetsCompat.CONSUMED
            }

            ViewCompat.requestApplyInsets(decorView)

            // Also set system nav bar color (required for proper contrast in some OEMs)
            window.navigationBarColor = navBarColor.toArgb()
        } else {
            // Android 14 and below
            window.statusBarColor = statusBarColor.toArgb()
            window.navigationBarColor = navBarColor.toArgb()
        }
    }

}

