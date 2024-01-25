package com.nora.savinggoal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.nora.savinggoal.ui.model.TopLevelDestinationModel
import com.nora.savinggoal.ui.screen.achievement.navigation.navigateToAchievement
import com.nora.savinggoal.ui.screen.coming.navigation.navigateToDashboard
import com.nora.savinggoal.ui.screen.coming.navigation.navigateToProfile
import com.nora.savinggoal.ui.screen.home.navigation.navigateToHome

class NavigationAction(private val navController: NavController) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    /*val currentTopLevelDestination: TopLevelDestinationModel?
        @Composable get() = when (currentDestination?.route) {
            HOME -> TopLevelDestinationModel.HOME
            DASHBOARD -> TopLevelDestinationModel.DASHBOARD
            ACHIEVEMENT -> TopLevelDestinationModel.ACHIEVEMENT
            PROFILE -> TopLevelDestinationModel.PROFILE
            else -> null
        }*/

    fun navigateToTopLevelDestination(destination: TopLevelDestinationModel) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (destination) {
            TopLevelDestinationModel.HOME -> navController.navigateToHome(topLevelNavOptions)
            TopLevelDestinationModel.DASHBOARD -> navController.navigateToDashboard(topLevelNavOptions)
            TopLevelDestinationModel.ACHIEVEMENT -> navController.navigateToAchievement(topLevelNavOptions)
            TopLevelDestinationModel.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
        }
    }
}

object AppTopLevelRoute {
    const val HOME = "Home_Graph"
    const val DASHBOARD = "Dashboard_Graph"
    const val ACHIEVEMENT = "Achievement_Graph"
    const val PROFILE = "Profile_Graph"
}

val TOP_LEVEL_DESTINATIONS: List<TopLevelDestinationModel> = TopLevelDestinationModel.entries