package com.moaimar.ricknmortyapp.features.locations.domain

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import javax.inject.Inject

class RefreshLocationsListUseCase @Inject constructor(private val repository: LocationsRepository) {
    suspend operator fun invoke(): Either<ErrorApp, List<LocationsFeed>> {
        return repository.refreshLocationsList().map { list ->
            list.map { locations ->
                locations.toFeed()
            }
        }
    }
}