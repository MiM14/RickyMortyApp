package com.moaimar.ricknmortyapp.features.characterslist.domain

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(private val repository: AppRepository) {
    suspend operator fun invoke(): Either<ErrorApp, List<CharactersFeed>> =
        repository.getFeed().map {
            it.map { character ->
                character.toFeed()
            }
        }
}

