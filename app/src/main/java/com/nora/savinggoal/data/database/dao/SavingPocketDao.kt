package com.nora.savinggoal.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nora.savinggoal.data.database.entity.SavingPocketEntity

@Dao
interface SavingPocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(savingPocketList: List<SavingPocketEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPocket(savingPocket: SavingPocketEntity)

    @Query("SELECT * FROM saving_goal_table")
    suspend fun getAllSavingPockets(): List<SavingPocketEntity>
}