package com.nora.savinggoal.ui.screen.new_goal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nora.savinggoal.R
import com.nora.savinggoal.domain.model.BankAccount
import com.nora.savinggoal.domain.model.GoalType
import com.nora.savinggoal.ui.component.FutureDatePicker
import com.nora.savinggoal.ui.component.GoalItem
import com.nora.savinggoal.ui.component.RobotoText
import com.nora.savinggoal.ui.theme.Color_Blank
import com.nora.savinggoal.ui.theme.Color_Harvest_Gold
import com.nora.savinggoal.ui.theme.Color_Jasper
import com.nora.savinggoal.util.calculateRemainingDays
import com.nora.savinggoal.util.noRippleClickable
import kotlinx.datetime.toJavaLocalDate
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun NewGoalScreen(
    modifier: Modifier,
    onSaveButtonClicked: () -> Unit,
    viewModel: NewGoalViewModel = koinViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        val goalTitle = remember { mutableStateOf("") }
        val goalType = remember { mutableStateOf(GoalType.TRAVEL) }

        NewGoalTitle(
            modifier = Modifier,
            onGoalFilled = {
                goalTitle.value = it
            }
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        GoalItemType(
            modifier = Modifier,
            onItemSelected = {
                goalType.value = it
            }
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        GoalDetail(
            modifier = Modifier,
            onSaveButtonClicked = { targetAmount, savingAmount, bankAccount, selectedDate, remainingDay ->
                viewModel.insertNewGoal(
                    viewModel.buildSavingPocketModel(
                        goalName = goalTitle.value,
                        goalType = goalType.value,
                        savingAmount = savingAmount,
                        targetAmount = targetAmount,
                        deadlineDate = selectedDate,
                        remainingDay = remainingDay,
                        bankAccount = bankAccount
                    )
                )
                onSaveButtonClicked()
            }
        )
    }
}

@Composable
private fun NewGoalTitle(
    modifier: Modifier,
    onGoalFilled: (String) -> Unit
) {
    var goalTitle by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color_Harvest_Gold)
            .padding(dimensionResource(id = R.dimen.margin_padding_16))
    ) {
        RobotoText(
            modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.margin_padding_8)),
            label = stringResource(id = R.string.label_what_your_goal),
            size = dimensionResource(id = R.dimen.text_size_24).value.sp,
            textColor = Color.White,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = goalTitle,
            onValueChange = {
                goalTitle = it
                onGoalFilled(it.text)
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black
            )
        )
    }
}

@Composable
private fun GoalItemType(
    modifier: Modifier,
    onItemSelected: (GoalType) -> Unit
) {
    var selectedItemIndex by remember { mutableIntStateOf(-1) }

    val mockingGoalType: List<GoalType> = GoalType.entries

    LazyHorizontalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        rows = GridCells.Adaptive(140.dp),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.margin_padding_16)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_padding_16))
    ) {
        itemsIndexed(
            items = mockingGoalType,
            key = { _, item ->
                item.id
            }
        ) { index, goal ->
            GoalItem(
                modifier = modifier,
                isItemSelected = selectedItemIndex == index,
                label = goal.label,
                icon = goal.icon
            ) {
                selectedItemIndex = index
                onItemSelected(goal)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GoalDetail(
    modifier: Modifier,
    onSaveButtonClicked: (Int, Int, String, String, Int) -> Unit
) {
    var targetAmount by remember { mutableStateOf(TextFieldValue("")) }
    var selectedDate by remember { mutableStateOf(TextFieldValue("")) }
    var bankAccount by remember { mutableStateOf("") }
    var savingPerMonth by remember { mutableStateOf(TextFieldValue("")) }
    var datePickerVisibleState by remember { mutableStateOf(false) }
    var remainingDay by remember { mutableIntStateOf(0) }
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    val bankAccounts: List<BankAccount> = BankAccount.entries

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.margin_padding_16))
    ) {

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = targetAmount,
            onValueChange = {
                targetAmount = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color_Jasper,
                unfocusedBorderColor = Color_Jasper,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            placeholder = {
                RobotoText(
                    modifier = modifier,
                    label = stringResource(id = R.string.label_amount),
                    size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                    textColor = Color.Gray
                )
            },
            suffix = {
                RobotoText(
                    modifier = modifier,
                    label = "THB",
                    size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                    textColor = Color_Jasper,
                )
            }
        )

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .noRippleClickable {
                    datePickerVisibleState = true
                },
            readOnly = true,
            enabled = false,
            value = selectedDate,
            onValueChange = {
                selectedDate = it
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color_Jasper,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledBorderColor = Color_Jasper,
                disabledContainerColor = Color.White,
                disabledTextColor = Color.Black,
                disabledPlaceholderColor = Color.Gray
            ),
            placeholder = {
                RobotoText(
                    modifier = modifier,
                    label = stringResource(id = R.string.label_select_date),
                    size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                    textColor = Color.Gray
                )
            },
            trailingIcon = {
                Icon(
                    modifier = modifier.size(dimensionResource(id = R.dimen.icon_size_large)),
                    painter = painterResource(id = R.drawable.ic_round_expand_more),
                    contentDescription = null,
                    tint = Color_Jasper
                )
            }
        )

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        RobotoText(
            modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.margin_padding_8)),
            label = stringResource(id = R.string.label_bank_account),
            size = dimensionResource(id = R.dimen.text_size_20).value.sp,
            textColor = Color.DarkGray
        )
        
        ExposedDropdownMenuBox(
            modifier = modifier.fillMaxWidth(),
            expanded = isDropDownMenuExpanded,
            onExpandedChange = {
                isDropDownMenuExpanded = !isDropDownMenuExpanded
            }
        ) {
            OutlinedTextField(
                modifier = modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
                value = bankAccount,
                onValueChange = {},
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color_Jasper,
                    unfocusedBorderColor = Color_Jasper,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    disabledBorderColor = Color_Jasper,
                    disabledContainerColor = Color.White,
                    disabledTextColor = Color.Black,
                    disabledPlaceholderColor = Color.Gray
                ),
                placeholder = {
                    RobotoText(
                        modifier = modifier,
                        label = stringResource(id = R.string.label_select_account),
                        size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                        textColor = Color.Gray
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = modifier.size(dimensionResource(id = R.dimen.icon_size_large)),
                        painter = painterResource(id = R.drawable.ic_round_expand_more),
                        contentDescription = null,
                        tint = Color_Jasper
                    )
                }
            )

            ExposedDropdownMenu(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White),
                expanded = isDropDownMenuExpanded,
                onDismissRequest = { isDropDownMenuExpanded = false }
            ) {
                bankAccounts.forEach { account ->
                    DropdownMenuItem(
                        modifier = modifier.fillMaxWidth(),
                        text = { RobotoText(modifier = modifier, label = account.bankName) },
                        onClick = {
                            bankAccount = account.bankName
                            isDropDownMenuExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = savingPerMonth,
            onValueChange = {
                savingPerMonth = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color_Jasper,
                unfocusedBorderColor = Color_Jasper,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            placeholder = {
                RobotoText(
                    modifier = modifier,
                    label = stringResource(id = R.string.label_saving_amount),
                    size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                    textColor = Color.Gray
                )
            },
            suffix = {
                RobotoText(
                    modifier = modifier,
                    label = "THB/Month",
                    size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                    textColor = Color_Jasper,
                )
            }
        )

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                onSaveButtonClicked(
                    targetAmount.text.toInt(),
                    savingPerMonth.text.toInt(),
                    bankAccount,
                    selectedDate.text,
                    remainingDay
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color_Jasper
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8))
        ) {
            RobotoText(
                modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.margin_padding_8)),
                label = "Save",
                size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                textColor = Color.White,
            )
        }

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        if (datePickerVisibleState) {
            FutureDatePicker(
                modifier = modifier,
                onDateSelected = {
                    val dateFormat = DateTimeFormatter.ofPattern(
                        "dd/MMM/yyyy",
                        Locale.getDefault()
                    )
                    selectedDate = TextFieldValue(it.toJavaLocalDate().format(dateFormat))
                    remainingDay = calculateRemainingDays(it)
                },
                onDismiss = {
                    datePickerVisibleState = false
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun NewGoalScreenPreview() {
    NewGoalScreen(
        modifier = Modifier,
        onSaveButtonClicked = {

        }
    )
}