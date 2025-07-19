package com.ttm.cmpdemoapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import org.jetbrains.compose.resources.painterResource

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import cmpdemoapp.composeapp.generated.resources.Res
import cmpdemoapp.composeapp.generated.resources.home_selected
import cmpdemoapp.composeapp.generated.resources.home_unselected
import cmpdemoapp.composeapp.generated.resources.profile_setting_selected
import cmpdemoapp.composeapp.generated.resources.profile_setting_unselected
import cmpdemoapp.composeapp.generated.resources.setting_selected
import cmpdemoapp.composeapp.generated.resources.setting_unselected

import com.ttm.cmpdemoapp.AppRoute
import com.ttm.cmpdemoapp.AppRoute.DashboardGraph
import com.ttm.cmpdemoapp.AppRoute.ProfileGraph
import com.ttm.cmpdemoapp.core.presentation.theme.MARGIN_LARGE
import com.ttm.cmpdemoapp.core.presentation.theme.MARGIN_MEDIUM
import com.ttm.cmpdemoapp.core.presentation.theme.TEXT_REGULAR_2X
import com.ttm.cmpdemoapp.ui.dashboard.DashboardRoute
import com.ttm.cmpdemoapp.ui.profile.ProfileRoute
import com.ttm.cmpdemoapp.ui.setting.SettingRoute


@Composable
fun HomeRoute() {

    HomeScreen()
}

@Composable
fun HomeScreen() {

    val homeNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            HomeBottomNavigationBar(
                navController = homeNavController
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = homeNavController,
            startDestination = DashboardGraph
        ) {
            // Dashboard graph
            navigation<DashboardGraph>(
                startDestination = AppRoute.Dashboard
            ) {
                composable<AppRoute.Dashboard> {
                    DashboardRoute()
                }
            }

            // Account Graph
            navigation<AppRoute.SettingGraph>(
                startDestination = AppRoute.Setting
            ) {
                composable<AppRoute.Setting> {
                    SettingRoute()
                }
            }

            // Profile Setting Graph
            navigation<ProfileGraph>(
                startDestination = AppRoute.ProfileSetting
            ) {
                composable<AppRoute.ProfileSetting> {
                    ProfileRoute()
                }
            }
        }
    }
}
@Composable
fun HomeBottomNavigationBar(
    navController: NavHostController
) {
    // Backstack entry and current destination from NavController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Bottom Navigation Items. With their root graphs
    val bottomNavItemsWithRootGraphs = listOf<BottomNavItem>(
        BottomNavItem(
            selectedIcon = painterResource(Res.drawable.home_selected),
            unSelectedIcon = painterResource(Res.drawable.home_unselected),
            routeToCheckSelected = AppRoute.Dashboard,
            rootGraph = DashboardGraph,
            title = "Home"
        ),
        BottomNavItem(
            selectedIcon = painterResource(Res.drawable.setting_selected),
            unSelectedIcon = painterResource(Res.drawable.setting_unselected),
            routeToCheckSelected = AppRoute.Setting,
            rootGraph = AppRoute.SettingGraph,
            title = "Setting"
        ),
        BottomNavItem(
            selectedIcon = painterResource(Res.drawable.profile_setting_selected),
            unSelectedIcon = painterResource(Res.drawable.profile_setting_unselected),
            routeToCheckSelected = AppRoute.ProfileSetting,
            rootGraph = ProfileGraph,
            title = "Profile"
        ),
    )

    BottomAppBar(modifier = Modifier.fillMaxWidth(), containerColor = Color.White) {
        // Bottom nav items with root graph
        bottomNavItemsWithRootGraphs.forEach { navItem ->
            val selected = currentDestination?.hierarchy?.any { it.hasRoute(navItem.routeToCheckSelected::class) } == true
            NavigationBarItem(

                icon = {
                    CustomIconWithText(selected,navItem)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    indicatorColor = Color.Transparent
                ),
                onClick = {
                    navController.navigate(navItem.rootGraph) {
                        popUpTo(navController.graph.findStartDestination().route ?: "") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = selected
            )
        }

    }
}

@Composable
fun CustomIconWithText(selected: Boolean, navItem: BottomNavItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = if (selected) navItem.selectedIcon else navItem.unSelectedIcon,
            contentDescription = navItem.title,
            modifier = Modifier.size(MARGIN_LARGE)
        )
        Text(
            text = navItem.title,
            fontSize = TEXT_REGULAR_2X,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = MARGIN_MEDIUM)
        )
    }

}
data class BottomNavItem(
    val selectedIcon: Painter,
    val unSelectedIcon: Painter,
    val rootGraph: AppRoute,
    val routeToCheckSelected: AppRoute,
    val title : String
)