package com.nora.savinggoal.ui.model

import com.nora.savinggoal.R
import com.nora.savinggoal.ui.navigation.AppTopLevelRoute

enum class TopLevelDestinationModel(
    val route: String,
    val icon: Int,
    val displayBadge: Boolean,
    var badgeCount: Int? = null
) {
    HOME(
        route = AppTopLevelRoute.HOME,
        icon = R.drawable.ic_home,
        displayBadge = false
    ),
    DASHBOARD(
        route = AppTopLevelRoute.DASHBOARD,
        icon = R.drawable.ic_dashboard,
        displayBadge = false
    ),
    ACHIEVEMENT(
        route = AppTopLevelRoute.ACHIEVEMENT,
        icon = R.drawable.ic_achievement,
        displayBadge = false
    ),
    PROFILE(
        route = AppTopLevelRoute.PROFILE,
        icon = R.drawable.ic_profile,
        displayBadge = true
    )
}
