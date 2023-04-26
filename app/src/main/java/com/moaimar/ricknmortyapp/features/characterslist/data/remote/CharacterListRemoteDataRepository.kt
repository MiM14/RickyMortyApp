package com.moaimar.ricknmortyapp.features.characterslist.data.remote

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo

interface CharacterListRemoteDataRepository {
    suspend fun getCharacters(): Either<ErrorApp, List<CharacterInfo>>
    suspend fun getCharactersInfo(keyId: Int): Either<ErrorApp, CharacterInfo>

    suspend fun searchCharactersByKeyword(keyWord: String): Either<ErrorApp, List<CharacterInfo>>
}