package com.nora.savinggoal

import android.app.Application
import com.nora.savinggoal.di.databaseModule
import com.nora.savinggoal.di.networkModule
import com.nora.savinggoal.di.repositoryModule
import com.nora.savinggoal.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger(level = Level.ERROR)
            modules(listOf(networkModule, databaseModule, repositoryModule, viewModelModule))
        }
    }
}