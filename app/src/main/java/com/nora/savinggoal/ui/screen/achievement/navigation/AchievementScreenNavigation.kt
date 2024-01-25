package com.nora.savinggoal.ui.screen.achievement.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nora.savinggoal.ui.navigation.AppTopLevelRoute
import com.nora.savinggoal.ui.screen.achievement.AchievementScreen

fun NavController.navigateToAchievement(navOptions: NavOptions? = null) {
    this.navigate(AppTopLevelRoute.ACHIEVEMENT, navOptions)
}

fun NavGraphBuilder.achievementScreen() {
    composable(
        AppTopLevelRoute.ACHIEVEMENT,
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
        AchievementScreen(modifier = Modifier)
    }
}