package com.nora.savinggoal

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.nora.savinggoal.data.network.BadgeSocketClient
import com.nora.savinggoal.ui.screen.main.MainViewModel
import com.nora.savinggoal.ui.screen.main.SavingGoalApplication
import com.nora.savinggoal.ui.theme.SavingGoalTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SavingGoalTheme {
                SavingGoalApplication(modifier = Modifier)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disconnectBadgeService()
    }
}
