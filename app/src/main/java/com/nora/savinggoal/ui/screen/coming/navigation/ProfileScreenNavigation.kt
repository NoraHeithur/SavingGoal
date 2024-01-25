package com.nora.savinggoal.ui.screen.coming.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nora.savinggoal.ui.navigation.AppTopLevelRoute
import com.nora.savinggoal.ui.screen.coming.ComingSoonScreen

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(AppTopLevelRoute.PROFILE, navOptions)
}

fun NavGraphBuilder.profileScreen() {
    composable(
        AppTopLevelRoute.PROFILE,
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
        ComingSoonScreen(modifier = Modifier)
    }
}