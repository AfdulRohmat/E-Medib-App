package com.example.e_medib.features.home_feature.roomDatabase

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RekapDatabaseDao {
    @Query("SELECT * from rekap_table")
    fun getAllRekap(): Flow<List<RekapModelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRekap(rekap: RekapModelEntity)

    @Query("SELECT * from rekap_table where id=:id")
    suspend fun getRekapById(id: String): RekapModelEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRekap(rekap: RekapModelEntity)

}