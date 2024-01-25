package com.nora.savinggoal.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nora.savinggoal.domain.model.GoalType

@Entity(tableName = "saving_goal_table")
data class SavingPocketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val goal: String,
    val goalType: GoalType,
    val deadlineDate: String,
    val remainingAmount: Int,
    val targetAmount: Int,
    val remainingDay: Int,
    val bankAccount: String,
    var status: String = ""
)