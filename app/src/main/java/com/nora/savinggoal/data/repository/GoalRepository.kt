package com.nora.savinggoal.data.repository

import com.nora.savinggoal.data.database.dao.SavingPocketDao
import com.nora.savinggoal.data.database.mapper.toDomainModel
import com.nora.savinggoal.domain.mapper.toEntityModel
import com.nora.savinggoal.domain.model.SavingPocket
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

interface GoalRepository {
    suspend fun insertAllSavingToDatabase(savingPocket: List<SavingPocket>)
    suspend fun insertSavingData(savingPocket: SavingPocket)
    suspend fun getAllSavingPocket(): List<SavingPocket>
}

class GoalRepositoryImpl(
    private val savingDao: SavingPocketDao,
    private val ioDispatcher: CoroutineDispatcher
): GoalRepository {
    override suspend fun insertAllSavingToDatabase(savingPocket: List<SavingPocket>) {
        withContext(ioDispatcher) {
            coroutineScope {
                savingDao.insertAll(savingPocket.map { it.toEntityModel() })
            }
        }
    }

    override suspend fun insertSavingData(savingPocket: SavingPocket) {
        withContext(ioDispatcher) {
            coroutineScope {
                savingDao.insertPocket(savingPocket.toEntityModel())
            }
        }
    }

    override suspend fun getAllSavingPocket(): List<SavingPocket> {
        return withContext(ioDispatcher) {
            coroutineScope {
                savingDao.getAllSavingPockets().map { it.toDomainModel() }
            }
        }
    }
}