package com.nora.savinggoal.ui.screen.home.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nora.savinggoal.ui.navigation.AppTopLevelRoute
import com.nora.savinggoal.ui.screen.home.HomeScreen

const val HOME_ROUTE = "Home_Route"
const val CREATE_GOAL_ROUTE = "Create_Goal_Route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onNewGoalClicked: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = AppTopLevelRoute.HOME,
        startDestination = HOME_ROUTE
    ) {
        composable(
            route = HOME_ROUTE,
            enterTransition = {
                fadeIn(tween(400))
            },
            exitTransition = {
                fadeOut(tween(400))
            },
            popEnterTransition = {
                fadeIn(tween(400))
            },
            popExitTransition = {
                fadeOut(tween(400))
            }
        ) {
            HomeScreen(
                modifier = Modifier,
                onNewGoalButtonClicked = onNewGoalClicked
            )
        }
        nestedGraphs()
    }
}