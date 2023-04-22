package com.moaimar.ricknmortyapp.features.characterslist.domain

import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(keyId: Int) =
        repository.getDetail(keyId)

}

