package com.moaimar.ricknmortyapp.features.locations.domain

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import javax.inject.Inject

class GetLocationsListUseCase @Inject constructor(private val repository: LocationsRepository) {
    suspend operator fun invoke(): Either<ErrorApp, List<LocationsFeed>> {
        return repository.getLocationsList().map { list ->
            list.map { locations ->
                locations.toFeed()
            }
        }
    }
}