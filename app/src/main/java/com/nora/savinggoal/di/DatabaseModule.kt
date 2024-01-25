package com.nora.savinggoal.di

import androidx.room.Room
import com.nora.savinggoal.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private const val DATABASE_NAME = "Saving_Pocket_Database"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    single { get<AppDatabase>().savingPocketDao() }
}