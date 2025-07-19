package com.ttm.cmpdemoapp

import kotlinx.serialization.Serializable

sealed interface AppRoute {

    // Splash
    @Serializable
    data object Splash : AppRoute

    // Auth
    @Serializable
    data object AuthGraph : AppRoute

    @Serializable
    data object Login : AppRoute

    @Serializable
    data object CreateAccount : AppRoute

    @Serializable
    data object PersonalInformation : AppRoute

    // Home
    @Serializable
    data object Home : AppRoute

    // Dashboard
    @Serializable
    data object DashboardGraph : AppRoute

    @Serializable
    data object Dashboard : AppRoute

    //Setting
    @Serializable
    data object SettingGraph : AppRoute

    @Serializable
    data object Setting : AppRoute

    // Profile
    @Serializable
    data object ProfileGraph : AppRoute

    @Serializable
    data object ProfileSetting : AppRoute

    @Serializable
    data class ProfileDetails(val userId: Int) : AppRoute
}