package com.example.e_medib.features.home_feature.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.e_medib.utils.DateConverter
import com.example.e_medib.utils.UUIDConverter


@Database(entities = [RekapModelEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class RekapDatabase : RoomDatabase() {
    abstract fun rekapDao(): RekapDatabaseDao

}