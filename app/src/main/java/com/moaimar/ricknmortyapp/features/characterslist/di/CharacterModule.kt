package com.moaimar.ricknmortyapp.features.characterslist.di

import com.moaimar.ricknmortyapp.features.characterslist.data.CharacterListDataRepository
import com.moaimar.ricknmortyapp.features.characterslist.data.local.CharacterListLocalDataRepository
import com.moaimar.ricknmortyapp.features.characterslist.data.local.db.CharacterListLocalDataSource
import com.moaimar.ricknmortyapp.features.characterslist.data.remote.CharacterListRemoteDataRepository
import com.moaimar.ricknmortyapp.features.characterslist.data.remote.api.CharacterListRemoteDataSource
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CharacterModule {
    @Binds
    abstract fun bindCharacterRepository(repository: CharacterListDataRepository): CharacterRepository

    @Binds
    abstract fun bindCharacterLocalRepository(repository: CharacterListLocalDataSource): CharacterListLocalDataRepository

    @Binds
    abstract fun bindCharacterRemoteRepository(repository: CharacterListRemoteDataSource): CharacterListRemoteDataRepository
}