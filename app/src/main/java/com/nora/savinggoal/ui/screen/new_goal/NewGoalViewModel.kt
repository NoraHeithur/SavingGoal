package com.nora.savinggoal.ui.screen.new_goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nora.savinggoal.data.database.dao.SavingPocketDao
import com.nora.savinggoal.domain.mapper.toEntityModel
import com.nora.savinggoal.domain.model.GoalType
import com.nora.savinggoal.domain.model.SavingPocket
import com.nora.savinggoal.util.calculateProgress
import kotlinx.coroutines.launch

class NewGoalViewModel(
    private val savingPocketDao: SavingPocketDao
): ViewModel() {

    fun buildSavingPocketModel(
        goalName: String,
        goalType: GoalType,
        savingAmount: Int,
        targetAmount: Int,
        deadlineDate: String,
        remainingDay: Int,
        bankAccount: String = "Unknown"
    ): SavingPocket {
        return SavingPocket(
            goal = goalName,
            goalType = goalType,
            remainingAmount = savingAmount,
            targetAmount = targetAmount,
            deadlineDate = deadlineDate,
            remainingDay = remainingDay,
            bankAccount = bankAccount
        ).apply {
            status = mapStatus(calculateProgress(targetAmount, savingAmount))
        }
    }

    fun insertNewGoal(savingPocket: SavingPocket) {
        viewModelScope.launch {
            savingPocketDao.insertPocket(savingPocket.toEntityModel())
        }
    }
}