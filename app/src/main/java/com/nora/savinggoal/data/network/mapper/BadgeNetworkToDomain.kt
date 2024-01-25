package com.nora.savinggoal.data.network.mapper

import com.nora.savinggoal.data.network.model.BadgeNetwork
import com.nora.savinggoal.domain.model.Badge

fun BadgeNetwork.toDomainModel(): Badge {
    return Badge(
        count = count
    )
}

fun emptyBadge(): Badge {
    return Badge(count = 0)
}