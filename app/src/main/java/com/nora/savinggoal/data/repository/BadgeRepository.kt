package com.nora.savinggoal.data.repository

import com.nora.savinggoal.data.network.mapper.emptyBadge
import com.nora.savinggoal.data.network.mapper.toDomainModel
import com.nora.savinggoal.data.network.service.BadgeService
import com.nora.savinggoal.domain.model.Badge
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

interface BadgeRepository {
    suspend fun getBadgeCount(): Badge
}

class BadgeRepositoryImpl(
    private val badgeService: BadgeService,
    private val ioDispatcher: CoroutineDispatcher
): BadgeRepository {

    override suspend fun getBadgeCount(): Badge {
        return withContext(ioDispatcher) {
            coroutineScope {
                badgeService.getBadge().body()?.toDomainModel() ?: emptyBadge()
            }
        }
    }
}