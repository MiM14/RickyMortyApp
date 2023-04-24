package com.moaimar.ricknmortyapp.features.characterslist.di

import com.moaimar.ricknmortyapp.app.data.local.db.RnMDatabase
import com.moaimar.ricknmortyapp.features.characterslist.data.local.db.CharacterDao
import com.moaimar.ricknmortyapp.features.characterslist.data.remote.api.CharacterApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CharacterProvidesModule {

    @Provides
    @Singleton
    fun provideCharacterDao(rnMDatabase: RnMDatabase): CharacterDao {
        return rnMDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun provideCharacterApiEndPoint(retrofit: Retrofit): CharacterApiEndPoints {
        return retrofit.create(CharacterApiEndPoints::class.java)
    }
}