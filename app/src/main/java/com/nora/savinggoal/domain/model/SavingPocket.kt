package com.nora.savinggoal.domain.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.nora.savinggoal.ui.theme.Color_Golden_Gate
import com.nora.savinggoal.ui.theme.Color_Office_Green
import kotlinx.parcelize.Parcelize

@Parcelize
data class SavingPocket(
    val id: Int = 0,
    val goal: String,
    val goalType: GoalType,
    val remainingAmount: Int,
    val targetAmount: Int,
    val deadlineDate: String,
    val remainingDay: Int,
    val bankAccount: String,
    var status: String = ""
): Parcelable {
    fun mapStatus(progress: Double): String {
        return when (progress) {
            in 0.0..0.2 -> SavingStatus.POOR
            in 0.2..0.5 -> SavingStatus.UNHAPPY
            in 0.5..0.7 -> SavingStatus.GOOD
            else -> SavingStatus.EXCELLENCE
        }.name.lowercase().replaceFirstChar { it.uppercase() }
    }

    fun mapStatusColor(status: String): Color {
        return when (status.lowercase()) {
            SavingStatus.POOR.name.lowercase() -> Color_Golden_Gate
            SavingStatus.UNHAPPY.name.lowercase() -> Color_Golden_Gate
            SavingStatus.GOOD.name.lowercase() -> Color_Office_Green
            SavingStatus.EXCELLENCE.name.lowercase() -> Color_Office_Green
            else -> Color_Office_Green
        }
    }
}
