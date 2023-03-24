package com.moaimar.ricknmortyapp.app.di

import android.content.Context
import androidx.room.Room
import com.moaimar.ricknmortyapp.app.data.local.RnMDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): RnMDatabase {
        return Room.databaseBuilder(
            context,
            RnMDatabase::class.java,
            "Rick and Morty Database"
        ).fallbackToDestructiveMigration().build()
    }
}