package com.nora.savinggoal.data.database.mapper

import com.nora.savinggoal.data.database.entity.SavingPocketEntity
import com.nora.savinggoal.domain.model.SavingPocket

fun SavingPocketEntity.toDomainModel(): SavingPocket {
    return SavingPocket(
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