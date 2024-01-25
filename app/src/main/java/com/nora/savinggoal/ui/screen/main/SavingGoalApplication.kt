package com.nora.savinggoal.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nora.savinggoal.R
import com.nora.savinggoal.ui.model.TopLevelDestinationModel
import com.nora.savinggoal.ui.navigation.AppTopLevelRoute
import com.nora.savinggoal.ui.navigation.NavigationAction
import com.nora.savinggoal.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.nora.savinggoal.ui.screen.achievement.navigation.achievementScreen
import com.nora.savinggoal.ui.screen.coming.navigation.dashboardScreen
import com.nora.savinggoal.ui.screen.coming.navigation.profileScreen
import com.nora.savinggoal.ui.screen.home.navigation.homeScreen
import com.nora.savinggoal.ui.screen.new_goal.navigation.navigateToNewGoal
import com.nora.savinggoal.ui.screen.new_goal.navigation.newGoalScreen
import com.nora.savinggoal.ui.theme.Color_Blank
import com.nora.savinggoal.ui.theme.Color_Jasper
import com.nora.savinggoal.ui.theme.SavingGoalTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavingGoalApplication(
    modifier: Modifier
) {
    MainScreen(modifier = modifier)
}

@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()
    val navigateAction = remember(navController) {
        NavigationAction(navController = navController)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            MainNavigationBar(
                modifier = modifier,
                selectedDestination = navigateAction.currentDestination,
                navigateToTopLevelDestination = navigateAction::navigateToTopLevelDestination
            )
        }
    ) { innerPadding ->
        MainScreenContent(
            modifier = Modifier.padding(innerPadding),
            navController
        )
    }
}

@Composable
fun MainScreenContent(modifier: Modifier, navHostController: NavHostController) {
    TopLevelNavHost(modifier = modifier, navController = navHostController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigationBar(
    modifier: Modifier,
    selectedDestination: NavDestination?,
    navigateToTopLevelDestination: (TopLevelDestinationModel) -> Unit,
    viewModel: MainViewModel = koinViewModel()
) {
    Column {
        Divider(
            modifier = modifier,
            thickness = 1.dp,
            color = Color.LightGray
        )

        NavigationBar(
            modifier = modifier,
            containerColor = Color.White,
            tonalElevation = 10.dp
        ) {
            val badgeCount by viewModel.badgeState.collectAsStateWithLifecycle()
            TOP_LEVEL_DESTINATIONS.forEach { destination ->
                val selected = selectedDestination.isTopLevelDestinationInHierarchy(destination)
                NavigationBarItem(
                    icon = {
                        if (destination.displayBadge) {
                            destination.apply {
                                this.badgeCount = badgeCount
                            }
                            BadgedBox(
                                badge = {
                                    if (destination.badgeCount != null) {
                                        Badge {
                                            with(destination.badgeCount) {
                                                Text(
                                                    text = if (this!! < 100) {
                                                        this.toString()
                                                    } else {
                                                        "99+"
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    modifier = modifier.size(dimensionResource(id = R.dimen.icon_size_extra_small)),
                                    painter = painterResource(id = destination.icon),
                                    contentDescription = destination.route
                                )
                            }
                        } else {
                            Icon(
                                modifier = modifier.size(dimensionResource(id = R.dimen.icon_size_extra_small)),
                                painter = painterResource(id = destination.icon),
                                contentDescription = destination.route
                            )
                        }
                    },
                    label = null,
                    selected = selected,
                    onClick = { navigateToTopLevelDestination(destination) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color_Jasper,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color_Blank,
                    )
                )
            }
        }
    }
}

@Composable
private fun TopLevelNavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = AppTopLevelRoute.HOME,
    ) {
        homeScreen(
            onNewGoalClicked = navController::navigateToNewGoal,
            nestedGraphs = {
                newGoalScreen(
                    modifier = modifier,
                    onSaveButtonClicked = {
                        navController.popBackStack()
                    }
                )
            }
        )
        dashboardScreen()
        achievementScreen()
        profileScreen()
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestinationModel) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MainScreenPreview() {
    SavingGoalTheme {
        MainScreen(modifier = Modifier)
    }
}