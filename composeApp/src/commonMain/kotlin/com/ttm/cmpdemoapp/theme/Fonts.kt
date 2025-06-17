package com.ttm.cmpdemoapp.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import androidx.compose.material3.Typography
import cmpdemoapp.composeapp.generated.resources.Res
import cmpdemoapp.composeapp.generated.resources.roboto_bold
import cmpdemoapp.composeapp.generated.resources.roboto_medium
import cmpdemoapp.composeapp.generated.resources.roboto_regular
import cmpdemoapp.composeapp.generated.resources.roboto_semiBold

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CustomFontFamily(): FontFamily {
    return FontFamily(
        Font(resource = Res.font.roboto_regular, weight = FontWeight.Normal),
        Font(resource = Res.font.roboto_medium, weight = FontWeight.Medium),
        Font(resource = Res.font.roboto_semiBold, weight = FontWeight.SemiBold),
        Font(resource = Res.font.roboto_bold, weight = FontWeight.Bold)
    )
}

@Composable
fun CustomTypography() = Typography().run {

    val fontFamily = CustomFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}