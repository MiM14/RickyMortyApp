package com.moaimar.ricknmortyapp.features.characterslist.domain

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either

interface AppRepository {
    suspend fun getFeed(): Either<ErrorApp, List<CharacterInfo>>
    suspend fun getDetail(keyId: Int): Either<ErrorApp, CharacterInfo>

    suspend fun refreshFeed(): Either<ErrorApp, List<CharacterInfo>>
}