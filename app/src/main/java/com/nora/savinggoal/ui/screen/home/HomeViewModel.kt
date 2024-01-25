package com.nora.savinggoal.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nora.savinggoal.data.database.dao.SavingPocketDao
import com.nora.savinggoal.data.database.mapper.toDomainModel
import com.nora.savinggoal.domain.mapper.toEntityModel
import com.nora.savinggoal.domain.model.GoalType
import com.nora.savinggoal.domain.model.SavingPocket
import com.nora.savinggoal.util.calculateProgress
import com.nora.savinggoal.util.calculateRemainingDays
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDate

class HomeViewModel(
    private val savingPocketDao: SavingPocketDao
): ViewModel() {

    val goalItemState: MutableStateFlow<List<SavingPocket>> = MutableStateFlow(emptyList())

    init {
        initStarterGoal()
        getAllSavingPocket()
    }

    private fun initStarterGoal() {
        viewModelScope.launch {
            savingPocketDao.insertAll(listOf(
                SavingPocket(
                    id = 1,
                    goal = "Go to Japan",
                    goalType = GoalType.TRAVEL,
                    remainingAmount = 25000,
                    targetAmount = 170000,
                    deadlineDate = "2024-05-30".toLocalDate().toString(),
                    remainingDay = calculateRemainingDays("2024-05-30".toLocalDate()),
                    bankAccount = "Green",
                ).apply {
                    status = mapStatus(calculateProgress(targetAmount, remainingAmount))
                },
                SavingPocket(
                    id = 2,
                    goal = "Luxurious Omakase",
                    goalType = GoalType.FOOD,
                    remainingAmount = 5000,
                    targetAmount = 17000,
                    deadlineDate = "2024-12-10".toLocalDate().toString(),
                    remainingDay = calculateRemainingDays("2024-12-10".toLocalDate()),
                    bankAccount = "Purple",
                ).apply {
                    status = mapStatus(calculateProgress(targetAmount, remainingAmount))
                }
            ).map { it.toEntityModel() })
        }
    }

    fun getAllSavingPocket() {
        viewModelScope.launch {
            goalItemState.emit(savingPocketDao.getAllSavingPockets().map { it.toDomainModel() })
        }
    }
}