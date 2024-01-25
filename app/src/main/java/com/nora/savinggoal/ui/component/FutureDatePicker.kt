package com.nora.savinggoal.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.nora.savinggoal.R
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaZoneOffset
import kotlinx.datetime.toKotlinTimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlinx.datetime.until

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FutureDatePicker(
    modifier: Modifier,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val systemTimeZone = TimeZone.UTC
    val clockTime = Clock.System
    val minimumSelectDate = clockTime.todayIn(systemTimeZone)
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = clockTime.now().plus(2, DateTimeUnit.DAY, systemTimeZone)
            .toLocalDateTime(systemTimeZone)
            .toInstant(systemTimeZone).toEpochMilliseconds(),
        initialDisplayMode = DisplayMode.Picker
    )

    val selectedDate = datePickerState.selectedDateMillis?.let {
        Instant.fromEpochMilliseconds(it)
    }

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                modifier = modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.margin_padding_8)),
                onClick = {
                    onDateSelected(
                        selectedDate?.toLocalDateTime(systemTimeZone)?.date ?: minimumSelectDate
                    )
                    onDismiss()
            }) {
                RobotoText(
                    modifier = modifier,
                    label = "OK",
                    textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                RobotoText(
                    modifier = modifier,
                    label = "Cancel",
                    textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }
    ) {
        DatePicker(
            modifier = modifier,
            state = datePickerState,
            dateValidator = { timestamp ->
                timestamp > Clock.System.now().toEpochMilliseconds()
            },
            showModeToggle = false
        )
    }
}

@Preview
@Composable
fun FutureSelectPreview() {
    FutureDatePicker(modifier = Modifier, onDateSelected = {}, onDismiss = {})
}
