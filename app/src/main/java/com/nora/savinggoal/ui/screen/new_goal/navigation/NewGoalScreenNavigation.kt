package com.nora.savinggoal.ui.screen.new_goal.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nora.savinggoal.ui.navigation.AppTopLevelRoute
import com.nora.savinggoal.ui.screen.home.navigation.CREATE_GOAL_ROUTE
import com.nora.savinggoal.ui.screen.new_goal.NewGoalScreen

fun NavController.navigateToNewGoal() {
    this.navigate("${AppTopLevelRoute.HOME}/$CREATE_GOAL_ROUTE") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.newGoalScreen(
    modifier: Modifier,
    onSaveButtonClicked: () -> Unit,
) {
    composable(
        route = "${AppTopLevelRoute.HOME}/$CREATE_GOAL_ROUTE",
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
        NewGoalScreen(
            modifier = modifier,
            onSaveButtonClicked = onSaveButtonClicked,
        )
    }
}