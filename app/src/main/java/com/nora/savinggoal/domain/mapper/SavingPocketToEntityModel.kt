package com.nora.savinggoal.domain.mapper

import com.nora.savinggoal.data.database.entity.SavingPocketEntity
import com.nora.savinggoal.domain.model.SavingPocket

fun SavingPocket.toEntityModel(): SavingPocketEntity {
    return SavingPocketEntity(
        id = id,
        goal = goal,
        goalType = goalType,
        deadlineDate = deadlineDate,
        remainingAmount = remainingAmount,
        targetAmount = targetAmount,
        remainingDay = remainingDay,
        bankAccount = bankAccount,
        status = status
    )
}