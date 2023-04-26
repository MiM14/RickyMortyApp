package com.moaimar.ricknmortyapp.features.characterslist.data

import com.moaimar.ricknmortyapp.app.data.local.cache.AppCache
import com.moaimar.ricknmortyapp.app.data.local.cache.CHARACTERS_CACHE_KEY
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.app.funtional.right
import com.moaimar.ricknmortyapp.features.characterslist.data.local.CharacterListLocalDataRepository
import com.moaimar.ricknmortyapp.features.characterslist.data.remote.CharacterListRemoteDataRepository
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterRepository
import javax.inject.Inject

class CharacterListDataRepository @Inject constructor(
    private val localDataRepository: CharacterListLocalDataRepository,
    private val remoteDataRepository: CharacterListRemoteDataRepository,
    private val cache: AppCache
) : CharacterRepository {

    override suspend fun getFeed(): Either<ErrorApp, List<CharacterInfo>> {
        return if (cache.isCacheOutDated(CHARACTERS_CACHE_KEY)) {
            remoteDataRepository.getCharacters().map { remoteList ->
                localDataRepository.delete()
                localDataRepository.save(remoteList)
                cache.saveCacheDate(CHARACTERS_CACHE_KEY)
                remoteList
            }

        } else {
            localDataRepository.getCharacters().right()
        }
    }

    override suspend fun getDetail(keyId: Int): Either<ErrorApp, CharacterInfo> =
        localDataRepository.getCharactersInfo(keyId)?.right()
            ?: remoteDataRepository.getCharactersInfo(keyId).map { it }

    override suspend fun refreshFeed(): Either<ErrorApp, List<CharacterInfo>> {
        localDataRepository.delete()
        cache.refreshCache(CHARACTERS_CACHE_KEY)
        return getFeed()
    }

    override suspend fun getSearchedCharacters(keyWord: String): Either<ErrorApp, List<CharacterInfo>> {
        return remoteDataRepository.searchCharactersByKeyword(keyWord)
    }
}