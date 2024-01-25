package com.nora.savinggoal.di

import com.nora.savinggoal.data.repository.BadgeRepository
import com.nora.savinggoal.data.repository.BadgeRepositoryImpl
import com.nora.savinggoal.data.repository.GoalRepository
import com.nora.savinggoal.data.repository.GoalRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::GoalRepositoryImpl) { bind<GoalRepository>() }
    singleOf(::BadgeRepositoryImpl) { bind<BadgeRepository>() }
}