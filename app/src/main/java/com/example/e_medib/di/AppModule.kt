package com.example.e_medib.di

import android.content.Context
import androidx.room.Room
import com.example.e_medib.features.home_feature.roomDatabase.RekapDatabase
import com.example.e_medib.features.home_feature.roomDatabase.RekapDatabaseDao
import com.example.e_medib.network.EMedibApi
import com.example.e_medib.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // BUILD RETROFIT
    @Singleton
    @Provides
    fun provideEMedibApi(): EMedibApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(EMedibApi::class.java)
    }

    // BUILD ROOM DATABASE
    @Singleton
    @Provides
    fun providesRekapDao(rekapDatabase: RekapDatabase): RekapDatabaseDao = rekapDatabase.rekapDao()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): RekapDatabase =
        Room.databaseBuilder(context, RekapDatabase::class.java, "rekaps_db")
            .fallbackToDestructiveMigration().build()
}