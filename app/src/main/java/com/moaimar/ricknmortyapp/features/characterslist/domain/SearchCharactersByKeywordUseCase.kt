package com.moaimar.ricknmortyapp.features.characterslist.domain

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import javax.inject.Inject

class SearchCharactersByKeywordUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(keyWord: String): Either<ErrorApp, List<CharactersFeed>> =
        repository.getSearchedCharacters(keyWord).map {
            it.map { character ->
                character.toFeed()
            }
        }
}