package com.nora.savinggoal.di

import com.nora.savinggoal.ui.screen.home.HomeViewModel
import com.nora.savinggoal.ui.screen.main.MainViewModel
import com.nora.savinggoal.ui.screen.new_goal.NewGoalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::NewGoalViewModel)
    viewModelOf(::HomeViewModel)
}