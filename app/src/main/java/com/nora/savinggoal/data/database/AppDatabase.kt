package com.nora.savinggoal.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nora.savinggoal.data.database.dao.SavingPocketDao
import com.nora.savinggoal.data.database.entity.SavingPocketEntity

@Database(
    entities = [SavingPocketEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savingPocketDao(): SavingPocketDao
}